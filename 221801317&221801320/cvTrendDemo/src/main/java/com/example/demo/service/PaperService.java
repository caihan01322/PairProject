package com.example.demo.service;

import com.example.demo.dao.PaperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Paper;

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
}
