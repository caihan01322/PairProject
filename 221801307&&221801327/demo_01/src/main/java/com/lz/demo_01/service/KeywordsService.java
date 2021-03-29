package com.lz.demo_01.service;

import com.lz.demo_01.pojo.Keywords;

import java.util.List;

public interface KeywordsService {
    List<Keywords> findAllKeywords();
    List<Keywords> findAllKeywordsCVPR();
    List<Keywords> findAllKeywordsICCV();
    List<Keywords> findAllKeywordsECCV();
}
