package com.jdzy.papersearch.service;

import com.jdzy.papersearch.dao.KeywordDao;
import com.jdzy.papersearch.dao.MeetDao;
import com.jdzy.papersearch.dao.PaperDao;
import com.jdzy.papersearch.pojo.Meet;
import com.jdzy.papersearch.pojo.Paper;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EchartsService {
    @Autowired
    KeywordDao kDao;
    @Autowired
    PaperDao pDao;
    @Autowired
    MeetDao mDao;

    @Cacheable(value="sunburst",
            key = "'data'")
    public Map<String,Object> getSunburst(){
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy");
        Map<String,Object> res = new LinkedHashMap<>();
        List<Map<String,Object>> meetMapList = new ArrayList<>();

        List<Meet> meetList = mDao.getMeetList();
        for (Meet meet : meetList) {

            Map<String,Object> meetMap = new LinkedHashMap<>();
            List<Map<String,Object>> yearList = new ArrayList<>();
            for(int i=2017;i<=2020;i++){
                Date year=null;
                Map<String, Object> yearMap = new LinkedHashMap<>();
                try {
                    year = dataFormat.parse(String.valueOf(i));
                } catch (ParseException e) {
                    System.out.println("年份异常");
                }

                List<Map<String, Object>> keywords = kDao.findTopKeyword((Integer) meet.getId(),year , 5);
                for (Map<String, Object> keyword : keywords) {
                    keyword.put("name",keyword.get("keyword_name"));
                    keyword.put("value",keyword.get("keyword_num"));
                    List<Paper> paperList = pDao.findPaperByKeywordId((Integer) keyword.get("keyword_id"),
                            (Integer) meet.getId(),
                            year,
                            5);
                    List<Map<String,Object>> paperMapList = new ArrayList<>();
                    for (int j = 0; j < paperList.size(); j++) {
                        Map<String, Object> paperMap = new HashMap<>();
                        Integer num=paperList.size();
                        Long value= (Long) keyword.get("keyword_num");
                        paperMap.put("name",paperList.get(j).getTitle());
                        if(j!=paperMapList.size()-1){
                            paperMap.put("value",value/num);
                        }else{
                            paperMap.put("value",value-(value/num)*num);
                        }
                        paperMapList.add(paperMap);
                    }
                    keyword.put("children",paperMapList);
                }
                yearMap.put("name",String.valueOf(i));
                yearMap.put("children",keywords);
                yearList.add(yearMap);
            }
            meetMap.put("name",meet.getName());
            meetMap.put("children",yearList);
            meetMapList.add(meetMap);
        }
        res.put("sunburst",meetMapList);
        return res;
    }
    @Cacheable(value="top10",
            key = "'data'")
    public Map<String,Object> getTop10(){
        List<Map<String, Object>> topKeyword = kDao.findTopKeyword(null, null,10);
        HashMap<String, Object> resp = new HashMap<>();
        List<Integer> id = new ArrayList<>();
        List<String> name = new ArrayList<>();
        List<Integer> num = new ArrayList<>();
        List<List<Paper>> papers = new ArrayList<>();
        resp.put("keywordId",id);
        resp.put("keywordName",name);
        resp.put("keywordNum",num);
        resp.put("paperList",papers);
        for (Map<String, Object> word : topKeyword) {
            id.add(Integer.valueOf((Integer)word.get("keyword_id")));
            name.add((String)word.get("keyword_name"));
            num.add(((Long)word.get("keyword_num")).intValue());
            List<Paper> paper = pDao.findPaperByKeywordId((Integer) word.get("keyword_id"), null, null, 10);
            papers.add(paper);
        }
        return resp;
    }
}
