package com.fzu.service;
import com.fzu.pojo.Paper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PaperService {
    /*上传一篇论文*/
    //void uploadPaper(Paper paper);
    List<Paper> queryPaperByPage(Integer start,Integer rows);
    List<Paper> queryPaperByKeyword(String keyword,Integer start,Integer rows);
    List<Paper> queryPaperByAuthor(String Author,Integer start,Integer rows);
    List<Map<String,String>> queryTop10ByYear();
    void register(String username,String password);
    boolean login(String username,String password);
    void addLike(Integer userId,Integer paperId);
    List<Paper> queryLikes(Integer userId,Integer strat,Integer rows);

}
