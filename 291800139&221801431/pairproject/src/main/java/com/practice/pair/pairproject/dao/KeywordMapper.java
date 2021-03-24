package com.practice.pair.pairproject.dao;

import com.practice.pair.pairproject.pojo.Keyword;
import java.util.List;

public interface KeywordMapper {
    int deleteByPrimaryKey(Integer kpid);

    int insert(Keyword record);

    Keyword selectByPrimaryKey(Integer kpid);

    List<Keyword> selectAll();

    int updateByPrimaryKey(Keyword record);
}