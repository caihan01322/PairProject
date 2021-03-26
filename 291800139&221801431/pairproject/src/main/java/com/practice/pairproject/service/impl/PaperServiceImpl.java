package com.practice.pairproject.service.impl;

import com.practice.pairproject.dao.PaperMapper;
import com.practice.pairproject.pojo.Paper;
import com.practice.pairproject.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperMapper paperMapper;


    @Override
    public Integer insertPaper(Paper paper) {
        return paperMapper.insertPaper(paper);
    }

    @Override
    public int deleteByPrimaryKey(Integer pid) {
        return paperMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public int deleteByPrimaryKeyList(List<Integer> pids) {
        return paperMapper.deleteByPrimaryKeyList(pids);
    }
}
