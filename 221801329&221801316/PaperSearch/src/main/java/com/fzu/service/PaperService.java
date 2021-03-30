package com.fzu.service;
import com.alibaba.fastjson.JSONObject;
import com.fzu.pojo.Paper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PaperService {
    /*上传一篇论文*/
    //void uploadPaper(Paper paper);
    List<JSONObject> queryPaperByPage(Integer userId,Integer start,Integer rows);
    List<JSONObject> queryPaperByKeyword(Integer userId,String keyword,Integer start,Integer rows);
    List<JSONObject> queryPaperByAuthor(Integer userId,String author,Integer start,Integer rows);
    List<JSONObject> queryPaperByTitle(Integer userId,String title,Integer start,Integer rows);
    List<JSONObject> queryTop10ByYear();
    void register(String username,String password);
    Map<String,Integer> login(String username,String password);
    void addLike(Integer userId,Integer paperId);
    void deleteLike(Integer userId,Integer paperId);
    List<Paper> queryLikes(Integer userId,Integer strat,Integer rows);
    Integer isLike(Integer userId,Integer paperId);

}
