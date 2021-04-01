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

    public int getPaperNumByKeyword(Keyword param){
        return paperKeywordDao.getPaperNumByKeyword(param);
    }
    //通过词获取相应的文章列表
   public List<Paper> getPaperByKeywordLimit(Keyword param,int begin,int num){
       Map<String,String> paramMap = new HashMap<>();
       paramMap.put("keyword",param.getKeyword());
       paramMap.put("begin",String.valueOf(begin));
       paramMap.put("num",String.valueOf(num));
       paramMap.put("meeting",param.getMeeting());
        return paperKeywordDao.getPaperByKeywordLimit(paramMap);
   }

   public List<Keyword> getWordTrend(String keyword){
        return paperKeywordDao.getWordTrend(keyword,null);
   }
    public List<Keyword> getWordTrend(String keyword,String meeting){
        return paperKeywordDao.getWordTrend(keyword,meeting);
    }
    /**
     * 返回beginYear 到 endYear 的趋势map 年份为key 频率为value
     * @param keyword
     * @param beginYear
     * @param endYear
     * @return
     */
   public Map<String, Long> getWordTrendMap( String keyword, int beginYear, int endYear){
       List<Keyword> wordTrend = paperKeywordDao.getWordTrend(keyword,null);
       Map<String, Long> yearFrequencyMap = wordTrend.stream().collect(Collectors.toMap(Keyword::getYear, Keyword::getFrequency));
       for (int i = beginYear; i < endYear ; i++){
           if (yearFrequencyMap.get(String.valueOf(i))==null){
               yearFrequencyMap.put(String.valueOf(i),0L);
           }
       }
       return yearFrequencyMap;
   }
    public Map<String, Long> getWordTrendMap( String keyword,String meeting, int beginYear, int endYear){
        List<Keyword> wordTrend = paperKeywordDao.getWordTrend(keyword,meeting);
        Map<String, Long> yearFrequencyMap = wordTrend.stream().collect(Collectors.toMap(Keyword::getYear, Keyword::getFrequency));
        for (int i = beginYear; i < endYear ; i++){
            if (yearFrequencyMap.get(String.valueOf(i))==null){
                yearFrequencyMap.put(String.valueOf(i),0L);
            }
        }
        return yearFrequencyMap;
    }

    /**
     * 返回近五年的趋势map 年份为key 频率为value 空的补0
     * @return
     */
    public Map<String, Long> getWordTrendRecent(String keyword){
        Calendar cur = Calendar.getInstance();
        return getWordTrendMap(keyword,cur.get(Calendar.YEAR)-5,cur.get(Calendar.YEAR)) ;
    }

    public Map<String, Long> getWordTrendRecent(String keyword,String meeting){
        Calendar cur = Calendar.getInstance();
        return getWordTrendMap(keyword,meeting,cur.get(Calendar.YEAR)-5,cur.get(Calendar.YEAR)) ;
    }

   public List<Keyword> getHotWordResentYear(){
       Calendar cur = Calendar.getInstance();
       List<Keyword> orderedWord = paperKeywordDao.getOrderedWord(cur.get(Calendar.YEAR)-5,cur.get(Calendar.YEAR),10,null);
       return orderedWord;
   }


    public List<Keyword> getHotWordResentYear(String meeting){
        if (meeting==null) meeting="";
        String solvedMeetingStr = meeting.trim().toUpperCase();
        if (solvedMeetingStr.isEmpty()||solvedMeetingStr.equals("ICCV")||solvedMeetingStr.equals("ECCV")||solvedMeetingStr.equals("CVPR")) {
            Calendar cur = Calendar.getInstance();
            List<Keyword> orderedWord = paperKeywordDao.getOrderedWord(cur.get(Calendar.YEAR) - 5, cur.get(Calendar.YEAR), 10, solvedMeetingStr);
            return orderedWord;
        }
        return new ArrayList<>();
    }
}
