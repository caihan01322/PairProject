package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import pojo.Paper;

import java.util.List;

@Mapper
public interface PaperDao {
    public void add(Paper paper);
    public List<Paper> list();
    public List<Paper> query(String keyword);


}
