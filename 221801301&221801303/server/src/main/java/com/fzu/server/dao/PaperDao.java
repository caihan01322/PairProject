package com.fzu.server.dao;

import com.fzu.server.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PaperDao {
    //读入数据库
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

    //检验是否有重复
    int checkKeyword(int pID,String keyword);
    int checkAuthor(int pID,String author);

    List<Paper> getPaperByName(String name,int start,int lim);//通过文章名查询
    List<Paper> getPaperByAuthor(String author,int start,int lim);//通过作者名查询
    List<Paper> getPaperByKeyword(String keyword,int start,int lim);//通过关键词查询
    int getCount(String str,int state);//获取查询的总数(state:0为文章名查询，1为作者，2为关键词)

    Paper getDetail(int id);//通过id查询
    List<String> getKeyword(Integer id);//通过id获得keyword
    List<String> getAuthor(Integer id);//通过id获得author

    List<Map<String,String>> getCVPR();//获得CVPR会议TOP10
    List<Map<String,String>> getECCV();//获得ECCV会议TOP10
    List<Map<String,String>> getICCV();//获得ICCV会议TOP10
    List<Map<String,String>> getTOP();//获取全部会议TOP10
}
