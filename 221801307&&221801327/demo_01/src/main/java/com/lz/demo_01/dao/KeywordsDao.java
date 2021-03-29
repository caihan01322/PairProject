package com.lz.demo_01.dao;

import com.lz.demo_01.pojo.Keywords;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface KeywordsDao {
    List<Keywords> findAllKeywords();
    List<Keywords> findAllKeywordsCVPR();
    List<Keywords> findAllKeywordsICCV();
    List<Keywords> findAllKeywordsECCV();
}
