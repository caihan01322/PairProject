package com.fzu.server.dao;

import com.fzu.server.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaperDao {
    void addECCVPaper(Paper paper);
    void addECCVKeyword(int pID,String keyword);

    void addCVPRPaper(Paper paper);
    void addCVPRKeyword(int pID,String keyword);
    void addCVPRAuthor(int pID,String author);

    void addICCVPaper(Paper paper);
    void addICCVKeyword(int pID,String keyword);
    void addICCVAuthor(int pID,String author);
}
