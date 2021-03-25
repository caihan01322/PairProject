package com.example.demo.dao;

import com.example.demo.pojo.Keyword;
import com.example.demo.pojo.PaperKeyword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pojo.Paper;

import java.util.List;
import java.util.Map;

/**
 * 1、 统计之后 找到十个topWord
 * 2、 word year meeting 统计frequency
 *
 */
@Mapper
public interface PaperKeywordDao {
    public void add(PaperKeyword keywordInfo);

    public List<String> queryByKeyword(Keyword keyword);

    public int deleteByPaperID(int paperID);


//    <!--    返回按照词频排序的列表-->
    public List<Keyword> getOrderedWord(@Param("beginYear") int beginYear,@Param("endYear") int endYear,@Param("size")int size);

//    返回 keyword year frequency 列表
    public List<Keyword> getWordTrend(String keyword);

//    通过热词获取相关的文章
    public List<Paper> getPaperByKeyword(String keyword);
//    通过热词获取相关的文章 分页
//    String keyword
//    int begin
//    int num
    public List<Paper> getPaperByKeywordLimit(Map<String,String> paramMap);

//    通过文章ID获取相应的关键词列表
    public List<Keyword> getKeywordByPaper(int paperID);

}
