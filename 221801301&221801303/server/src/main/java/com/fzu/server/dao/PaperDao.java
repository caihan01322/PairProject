package com.fzu.server.dao;

import com.fzu.server.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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

    void addPaper(Paper paper);
    void addKeyword(int pID,String keyword);
    void addAuthor(int pID,String author);

    List<Paper> getPaperByName(String name,int start,int lim);
    List<Paper> getPaperByAuthor(String author,int start,int lim);
    int getCount(String str,int state);

    List<String> getKeyword(Integer id);
    List<String> getAuthor(Integer id);

    List<Map<String,String>> getCVPR();
    List<Map<String,String>> getECCV();
    List<Map<String,String>> getICCV();
}
