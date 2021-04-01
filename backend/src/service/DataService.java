package com.eepractice.webcrawller.service;


import com.eepractice.webcrawller.bean.Item;
import com.eepractice.webcrawller.bean.Keyword;
import com.eepractice.webcrawller.bean.ResultItem;
import com.eepractice.webcrawller.repository.ResultItemRepository;
import com.eepractice.webcrawller.utils.CommonUtils;
import com.eepractice.webcrawller.utils.DataUtils;
import com.eepractice.webcrawller.utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DataService {


    @Autowired
    ResultItemRepository itemRepository;

    private static final String IEEE = "IEEE";
    private static final String ECCV = "ECCV";
    private final Pattern pattern = Pattern.compile("\\d{4}$");
    private List<ResultItem> allResultItems = new ArrayList<>();


    private void initItemsData(){
        allResultItems = itemRepository.findAll();
    }


    /**
     * 将年份信息提取出来
     * @param originDate
     * @return
     */
    public String convertDate(String originDate){
        Matcher matcher = pattern.matcher(originDate);
        String s = matcher.replaceAll("");
        return originDate.replace(s,"").trim();
    }


    /**
     * 根据会议名获取所有年份
     * @param meetingName
     * @return
     */
    public Set<Integer> getYears(String meetingName){
        Set<Integer> yearSet = new TreeSet<>();
        if (allResultItems.size() > 0){
            allResultItems.forEach(item -> {
                if (item.getPublisher().trim().equals(meetingName)){
                    yearSet.add(Integer.valueOf(convertDate(item.getConferenceDate())));
                }
            });
        }
        return yearSet;
    }

    /**
     *
     * @param year
     * @param targetList
     */
    public void countWordAndSave(String meeting,Integer year,List<Keyword> targetList){
        List<ResultItem> ieeeResultList = new ArrayList<>();
        allResultItems.forEach(item -> {
            if (item.getPublisher().trim().equals(meeting) && item.getConferenceDate().contains(String.valueOf(year))){
                ieeeResultList.add(item);
            }
        });
        List<Item> ieeeItems = CommonUtils.convertResultItemCollection(ieeeResultList);
        Map<String, Integer> yearData = DataUtils.countKeyWord(ieeeItems);
        // 只取最高的一次引用
        for (Map.Entry<String, Integer> entry : yearData.entrySet()) {
            targetList.add(new Keyword(entry.getKey(), entry.getValue()));
            break;
        }
    }



    // (IEEE,(2000,xxx,2001,xxx))
    public Map<String,Object> analyseDataAndGet(){
        // 获取所有数据
        initItemsData();
        long start = System.currentTimeMillis();
        Map<String,Object> resultMap = new HashMap<>();
        // 获得每一年年份
        Set<Integer> IEEEyears = getYears(IEEE);
        Set<Integer> ECCVyears = getYears(ECCV);
        List<Keyword> ieeeList = new LinkedList<>();
        List<Keyword> eccvLsit = new LinkedList<>();
        // 分析IEEE数据
        for (Integer ieeEyear : IEEEyears) {
            countWordAndSave(IEEE,ieeEyear,ieeeList);
        }
        // 分析ECCV
        for (Integer eccVyear : ECCVyears) {
            countWordAndSave(ECCV,eccVyear,eccvLsit);
        }
        resultMap.put("ieeeYears",IEEEyears);
        resultMap.put("eccvYears",ECCVyears);
        resultMap.put(IEEE,ieeeList);
        resultMap.put(ECCV,eccvLsit);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return resultMap;
    }

    public Pair<Integer, List<Keyword>> getKeywordTop(int n){
        List<Object[]> resultItems = itemRepository.getAll();
        List<Item> itemList = EntityUtils.transformEntity(resultItems);
        Map<String, Integer> countedMap = DataUtils.countKeyWord(itemList);
        List<Keyword> countedList = new ArrayList<>();
        int i = 0;
        for (Map.Entry<String, Integer> entry : countedMap.entrySet()) {
            if (i == n) break;
            countedList.add(new Keyword(entry.getKey(),entry.getValue()));
            i++;
        }
        Integer recordsNum = resultItems.size();
        return Pair.of(recordsNum,countedList);
    }



}

