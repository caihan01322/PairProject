package com.pair.service;

import com.pair.dao.PaperKeywordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperKeywordService {
    @Autowired
    PaperKeywordMapper paperKeywordMapper;
    public void deletePKByPid(String pid){
        paperKeywordMapper.deletePKByPid(pid);
    }
}
