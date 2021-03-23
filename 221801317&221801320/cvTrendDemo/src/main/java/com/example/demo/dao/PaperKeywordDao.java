package com.example.demo.dao;

import com.example.demo.pojo.Keyword;
import com.example.demo.pojo.PaperKeyword;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 1、 统计之后 找到十个topWord
 * 2、 word year meeting 统计frequency
 * 3、 word
 *
 */
@Mapper
public interface PaperKeywordDao {
    public void add(PaperKeyword keywordInfo);

    public List<String> queryByKeyword(Keyword keyword);

    public int deleteByPaperID(int paperID);


//    <!--    返回近五年 前10热词列表-->
    public List<Keyword> getHotWordResentYear();

//    返回 keyword year frequency 列表
    public List<Keyword> getWordTrend(String keyword);


}
