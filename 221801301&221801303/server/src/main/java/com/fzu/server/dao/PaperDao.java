package com.fzu.server.dao;

import com.fzu.server.pojo.ECCV;
import com.fzu.server.pojo.ECCVKeyword;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaperDao {
    void addPaper(ECCV paper);
    void addKeyword(String keyword);
}
