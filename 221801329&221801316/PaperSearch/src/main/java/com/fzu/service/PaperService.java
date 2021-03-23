package com.fzu.service;

import com.fzu.mapper.PaperMapper;
import com.fzu.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaperService {
    @Autowired
    PaperMapper paperMapper;
    /*上传一篇论文*/
    public void uploadPaper(Paper paper){
        //添加论文的信息
        paperMapper.addPaper(paper);
        //获得返回的主键id
        Integer id=paper.getId();
        //添加论文与作者的关联关系(一篇论文有多个作者)
        List<String> authors =paper.getAuthor();
        for (String author : authors) {
            paperMapper.insertAuthorWithId(id,author);
        }
        //添加论文与关键词的关联关系
        List<String> keywords = paper.getKeywords();
        for (String keyword : keywords) {
            paperMapper.insertKeywordWithId(id,keyword);
        }

    }
}
