package com.practice.pairproject.service.impl;

import com.practice.pairproject.dao.KeywordMapper;
import com.practice.pairproject.dao.PaperMapper;
import com.practice.pairproject.pojo.Keyword;
import com.practice.pairproject.pojo.KeywordVO;
import com.practice.pairproject.pojo.Paper;
import com.practice.pairproject.service.KeywordService;
import com.practice.pairproject.service.PaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class KeywordServiceImpl implements KeywordService {

    static private Calendar date = Calendar.getInstance();
    static private int nowYear;

    @Autowired
    private KeywordMapper keywordMapper;


    @Override
    public int insertPKeywords(Keyword kw) {
        return keywordMapper.insertPKeywords(kw);
    }

    @Override
    public List<Integer> searchKeywords(String kw) {
        return keywordMapper.searchKeywords(kw);
    }

    @Override
    public List<KeywordVO> selectAllTOPKeyword(String years, Integer topNum) {
        //当年年份
        nowYear = date.get(Calendar.YEAR);
        log.info("【当前年份】："+ nowYear);
        // > 年份
        int year = nowYear-Integer.valueOf(years);
        log.info("【> year】："+ year);
        List<KeywordVO> keywordVOList= keywordMapper.selectAllTOPKeyword(String.valueOf(year), topNum);
        return keywordVOList;
    }

    @Override
    public List<KeywordVO> selectTopKeyword(String meeting, String years, Integer topNum) {
        //当年年份
        nowYear = date.get(Calendar.YEAR);
        log.info("【当前年份】："+ nowYear);
        // > 年份
        int year = nowYear-Integer.valueOf(years);
        log.info("【> year】："+ year);
        List<KeywordVO> keywordVOList= keywordMapper.selectTopKeyword(meeting, String.valueOf(year), topNum);
        return keywordVOList;
    }

    @Override
    public List<KeywordVO> selectTOPKeywordXYear(Map<String, String> paramMap, List<KeywordVO> kList) {
        List<KeywordVO> keywordVOList = new ArrayList<>();
        for (KeywordVO kv : kList) {
            paramMap.put("content", kv.getContent());
            KeywordVO temp = keywordMapper.selectTOPKeywordXYear(paramMap);
            if(temp.getContent() == null){
                temp.setContent(kv.getContent());
            }
            keywordVOList.add(temp);
        }
        return keywordVOList;
    }

    @Override
    public List<Keyword> selectByPid(Integer pid) {
        return keywordMapper.selectByPid(pid);
    }

}
