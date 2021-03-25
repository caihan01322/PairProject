package com.practice.pairproject.service.impl;

import com.practice.pairproject.dao.PaperMapper;
import com.practice.pairproject.dao.UserMapper;
import com.practice.pairproject.pojo.Paper;
import com.practice.pairproject.pojo.User;
import com.practice.pairproject.service.PaperService;
import com.practice.pairproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperMapper paperMapper;


    @Override
    public Integer insertPaper(Paper paper) {
        return paperMapper.insertPaper(paper);
    }
}
