package com.example.demo.dao;

import com.example.demo.pojo.Keyword;
import org.apache.ibatis.annotations.Mapper;
import pojo.Paper;

import java.util.List;

@Mapper
public interface PaperDao {
    public void add(Paper paper);
    //public List<Paper> list();

    public List<Paper> query(Keyword keyword);


    public int getID(Paper paper);

    public int deleteByID(int paperID);

    public List<Paper> getPaperByKeyword(String keyword);

}
