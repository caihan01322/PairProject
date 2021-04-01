package com.fzu.service;
import com.alibaba.fastjson.JSONArray;
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
    JSONObject queryPaperByPage(Integer userId,Integer start,Integer rows);
    JSONObject queryPaperByKeyword(Integer userId,String keyword,Integer start,Integer rows);
    JSONObject queryPaperByAuthor(Integer userId,String author,Integer start,Integer rows);
    JSONObject queryPaperByTitle(Integer userId,String title,Integer start,Integer rows);
    List<JSONObject> queryTop10ByYear();
    void register(String username,String password);
    Map<String,Integer> login(String username,String password);
    void addLike(Integer userId,Integer paperId);
    void deleteLike(Integer userId,Integer paperId);
    JSONObject queryLikes(Integer userId,Integer start,Integer rows);
    Integer isLike(Integer userId,Integer paperId);
    Integer countAllByKeyword(String keyword);
    Integer countAllByAuthor(String author);
    Integer countAllByTitle(String title);
    Integer countAllByLike(Integer userId);
    JSONArray getKeywordTrends();
}
