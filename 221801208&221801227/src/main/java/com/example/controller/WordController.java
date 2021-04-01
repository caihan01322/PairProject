package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.dao.HotWordFrequencyDao;
import com.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/WordController")
public class WordController {

    @Autowired
    private HotWordFrequencyDao hotWordFrequencyDao;

    /**
     * 将单词插入hashmap
     * @param curWord
     * @param map
     */
    public void wordToHashMap(String curWord,Map<String, Integer> map){
        curWord = curWord.toLowerCase();
        Integer times = map.get(curWord);
        //times==null说明这个单词已经插入hashmap
        if(times != null)
            map.put(curWord,times+1);
        else
            map.put(curWord,1);
    }

    /**
     * 统计频率最高的10个单词的出现次数
     * @param set
     * @param map
     * @return
     * @throws IOException
     */
    public JSONObject frequency(Set<Word> set,Map<String, Integer> map) throws IOException{
        JSONObject result = new JSONObject();
        JSONArray hotWord= new JSONArray();
        JSONArray frequency = new JSONArray();
        Iterator<Map.Entry<String, Integer>> iterator1 = map.entrySet().iterator();
        while (iterator1.hasNext()) {
            Map.Entry<String, Integer> entry = iterator1.next();
            Word w = new Word(entry.getKey(),entry.getValue());
            set.add(w);
        }
        Iterator<Word> iterator2 = set.iterator();
        int i = 0;
        while (iterator2.hasNext()) {
            Word w = iterator2.next();
            hotWord.add(w.word);
            frequency.add(w.frequency);
            i++;
            if(i >= 10)
                break;
        }
        result.put("hotWord",hotWord);
        result.put("frequency",frequency);
        return result;
    }

    /**
     * 获取十大热词及其对应频率
     * @return
     * @throws IOException
     */
    @RequestMapping("/getHotWords")
    public JSONObject getHotWords() throws IOException{
        Map<String, Integer> map = new HashMap<>();
        Set<Word> set = new TreeSet<>();
        for(int i = 0;i < 3;i++) {
            List<ConferenceKwd> conferenceKwdList = null;
            if(i == 0){
                conferenceKwdList = hotWordFrequencyDao.getCvprKwd();
            }
            else if(i == 1){
                conferenceKwdList = hotWordFrequencyDao.getEccvKwd();
            }
            else if(i == 2){
                conferenceKwdList = hotWordFrequencyDao.getIccvKwd();
            }
            for (int j = 0; j < conferenceKwdList.size(); j++) {
                ConferenceKwd conferenceKwd = conferenceKwdList.get(j);
                wordToHashMap(conferenceKwd.getKeyword(), map);
            }
        }

        return frequency(set,map);
    }
}
