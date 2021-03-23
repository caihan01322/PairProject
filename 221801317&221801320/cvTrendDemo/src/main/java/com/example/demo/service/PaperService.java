package com.example.demo.service;

import com.example.demo.dao.PaperDao;
import com.example.demo.pojo.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Paper;

import java.util.List;

@Service
public class PaperService {

    @Autowired
    PaperDao paperDao;

    public void add(Paper paper){
        paperDao.add(paper);
    }

    public int getID(Paper paper){
        return paperDao.getID(paper);
    }

    public List<Paper> queryByKeyword(String keyword){
        Keyword keyword1 = new Keyword();
        keyword1.setKeyword(keyword);
        return paperDao.query(keyword1);
    }
}
