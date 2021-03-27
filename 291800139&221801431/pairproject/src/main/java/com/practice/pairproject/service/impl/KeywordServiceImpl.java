package com.practice.pairproject.service.impl;

import com.practice.pairproject.dao.KeywordMapper;
import com.practice.pairproject.dao.PaperMapper;
import com.practice.pairproject.pojo.Keyword;
import com.practice.pairproject.pojo.Paper;
import com.practice.pairproject.service.KeywordService;
import com.practice.pairproject.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordServiceImpl implements KeywordService {

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
}
