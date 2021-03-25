package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import pojo.Paper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PaperDao {
    public void add(Paper paper);
    //public List<Paper> list();

    //模糊查询 用like对标题和概述进行查询
    public List<Paper> queryByKeyword(String keyword);
//    通过关键词搜索获取相关的文章 分页
//    String keyword
//    int begin
//    int num
    public List<Paper> queryByKeywordLimit(Map<String,String> param);

    //精确查询
    public List<Paper> query(Paper paper);
//    通过关键词搜索获取相关的文章 分页
//    String keyword
//    int begin
//    int num
    public List<Paper> queryLimit(Map<String,String> param);

    public int getID(Paper paper);

    public int deleteByID(int paperID);



}
