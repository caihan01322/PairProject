package com.lz.demo_01.service;

import com.lz.demo_01.dao.KeywordsDao;
import com.lz.demo_01.pojo.Keywords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordsServiceImpl implements KeywordsService {
    @Autowired
    private KeywordsDao keywordsdao;

    public List<Keywords> findAllKeywords() {
        return keywordsdao.findAllKeywords();
    }

    ;

    public List<Keywords> findAllKeywordsCVPR() {
        return keywordsdao.findAllKeywordsCVPR();
    }

    public List<Keywords> findAllKeywordsICCV() {
        return keywordsdao.findAllKeywordsICCV();
    }

    public List<Keywords> findAllKeywordsECCV() {
        return keywordsdao.findAllKeywordsECCV();
    }
}
