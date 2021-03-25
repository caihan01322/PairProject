package com.example.demo.service;

import com.example.demo.dao.PaperKeywordDao;
import com.example.demo.pojo.Keyword;
import com.example.demo.pojo.PaperKeyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Paper;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PaperKeywordService {
    @Autowired
    PaperKeywordDao paperKeywordDao;

    public void add(PaperKeyword paperKeyword){
        paperKeywordDao.add(paperKeyword);
    }

    //通过词获取相应的文章列表
   public List<Paper> getPaperByKeywordLimit(String keyword,int begin,int num){
       Map<String,String> paramMap = new HashMap<>();
       paramMap.put("keyword",keyword);
       paramMap.put("begin",String.valueOf(begin));
       paramMap.put("num",String.valueOf(num));
        return paperKeywordDao.getPaperByKeywordLimit(paramMap);
   }

   public List<Keyword> getWordTrend(String keyword){
        return paperKeywordDao.getWordTrend(keyword);
   }

    /**
     * 返回beginYear 到 endYear 的趋势map 年份为key 频率为value
     * @param keyword
     * @param beginYear
     * @param endYear
     * @return
     */
   public Map<String, Long> getWordTrendMap( String keyword, int beginYear, int endYear){
       List<Keyword> wordTrend = paperKeywordDao.getWordTrend(keyword);
       Map<String, Long> yearFrequencyMap = wordTrend.stream().collect(Collectors.toMap(Keyword::getYear, Keyword::getFrequency));
       for (int i = beginYear; i < endYear ; i++){
           if (yearFrequencyMap.get(String.valueOf(i))==null){
               yearFrequencyMap.put(String.valueOf(i),0L);
           }
       }
       return yearFrequencyMap;
   }

    /**
     * 返回近五年的趋势map 年份为key 频率为value
     * @return
     */
    public Map<String, Long> getWordTrendRecent(String keyword){
        Calendar cur = Calendar.getInstance();
        return getWordTrendMap(keyword,cur.get(Calendar.YEAR)-5,cur.get(Calendar.YEAR)) ;
    }

   public List<Keyword> getHotWordResentYear(){
       Calendar cur = Calendar.getInstance();
       List<Keyword> orderedWord = paperKeywordDao.getOrderedWord(cur.get(Calendar.YEAR)-5,cur.get(Calendar.YEAR),10);
       return orderedWord;
   }

}
