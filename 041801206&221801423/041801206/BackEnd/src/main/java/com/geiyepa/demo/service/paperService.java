package com.geiyepa.demo.service;

import com.geiyepa.demo.entity.paper;
import com.geiyepa.demo.entity.paperWithBLOBs;
import com.geiyepa.demo.mapper.paperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Component
public class paperService implements paperMapper{

    @Autowired
    private paperMapper paperMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(paperWithBLOBs record) {
        return 0;
    }

    @Override
    public int insertSelective(paperWithBLOBs record) {
        return 0;
    }

    @Override
    public paperWithBLOBs selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(paperWithBLOBs record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(paperWithBLOBs record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(paper record) {
        return 0;
    }

    @Override
    public List<paper> selectLikeWord(String word) {

        return paperMapper.selectLikeWord(word);
    }

    @Override
    public List<paper> selectLikeKeyword(String keyword) {
        return paperMapper.selectLikeKeyword(keyword);
    }

}
