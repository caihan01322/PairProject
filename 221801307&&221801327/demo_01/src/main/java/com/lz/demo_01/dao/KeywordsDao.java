package com.lz.demo_01.dao;

import com.lz.demo_01.pojo.Keywords;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface KeywordsDao {
    //查询所有关键词
    List<Keywords> findAllKeywords();

    //查询CVPR所有关键词
    List<Keywords> findAllKeywordsCVPR();

    //查询ICCV所有关键词
    List<Keywords> findAllKeywordsICCV();

    //查询ECCV所有关键词
    List<Keywords> findAllKeywordsECCV();
}
