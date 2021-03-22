package com.example.demo.dao;

import com.example.demo.pojo.PaperKeyword;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaperKeywordDao {
    public void add(PaperKeyword keywordInfo);

}
