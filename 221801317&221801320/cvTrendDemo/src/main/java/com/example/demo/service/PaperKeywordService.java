package com.example.demo.service;

import com.example.demo.dao.PaperKeywordDao;
import com.example.demo.pojo.PaperKeyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperKeywordService {
    @Autowired
    PaperKeywordDao paperKeywordDao;

    public void add(PaperKeyword paperKeyword){
        paperKeywordDao.add(paperKeyword);
    }
}
