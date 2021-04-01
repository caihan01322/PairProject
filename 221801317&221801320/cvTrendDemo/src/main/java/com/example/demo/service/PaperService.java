package com.example.demo.service;

import com.example.demo.dao.PaperDao;
import com.example.demo.dao.PaperKeywordDao;
import com.example.demo.pojo.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Paper;

import java.util.HashMap;
import java.util.List;

@Service
public class PaperService {

    @Autowired
    PaperDao paperDao;
    @Autowired
    PaperKeywordDao paperKeywordDao;

    public void add(Paper paper){
        paperDao.add(paper);
    }

    public int getID(Paper paper){
        return paperDao.getID(paper);
    }

    public List<Paper> queryByKeyword(String keyword){
        return paperDao.queryByKeyword(keyword);
    }

//    通过关键词搜索获取相关的文章 分页
//    String keyword
//    int begin
//    int num
    public List<Paper> queryByKeywordLimit(Keyword keyword,int begin,int num){
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("keyword",keyword.getKeyword());
        paramMap.put("meeting",keyword.getMeeting());
        paramMap.put("begin",String.valueOf(begin));
        paramMap.put("num",String.valueOf(num));
        return paperDao.queryByKeywordLimit(paramMap);
    }
    public int queryPaperNumByKeyword(Keyword keyword){
        return paperDao.queryByKeywordRezultNum(keyword);
    }

//    通过title搜索获取相关的文章 分页
//    String keyword
//    int begin
//    int num
    public List<Paper> queryByTitleLimit(String title,int begin,int num){
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("title",title);
        paramMap.put("begin",String.valueOf(begin));
        paramMap.put("num",String.valueOf(num));
        return paperDao.queryLimit(paramMap);
    }
    public int queryPaperNumByTitle(String title){
        Paper param = new Paper();
        param.setTitle(title);
        return paperDao.queryRezultNum(param);
    }

//    通过title搜索获取相关的文章 分页
//    String title
//    int begin
//    int num
    public List<Paper> query(Paper paper,int begin,int num){
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("title",paper.getTitle());
        paramMap.put("meeting",paper.getMeeting());
        paramMap.put("year",paper.getYear());
        paramMap.put("abstractContent",paper.getAbstractContent());
        if (paper.getAuthors()!=null) {
            String[] authors = paper.getAuthors().split(",");
            StringBuilder authorsBuilder = new StringBuilder();
            for (int i = 0; i < authors.length; i++) {
                authorsBuilder.append(authors[i].trim());
            }
            paramMap.put("authors", authorsBuilder.toString());
        }
        paramMap.put("begin",String.valueOf(begin));
        paramMap.put("num",String.valueOf(num));
        return paperDao.queryLimit(paramMap);
    }
    public int queryPaperNum(Paper param){
        return paperDao.queryRezultNum(param);
    }

    //删除文章 需要把相应关键字的也删除
    public void deletePaperByID(int paperID){
        paperDao.deleteByID(paperID);
        paperKeywordDao.deleteByPaperID(paperID);
    }
}
