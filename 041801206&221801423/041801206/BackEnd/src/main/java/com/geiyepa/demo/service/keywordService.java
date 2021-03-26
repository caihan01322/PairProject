package com.geiyepa.demo.service;

import com.geiyepa.demo.bean.keywordanalysis;
import com.geiyepa.demo.mapper.keywordanalysisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class keywordService implements keywordanalysisMapper {
    @Autowired keywordanalysisMapper keywordanalysisMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(keywordanalysis record) {
        return 0;
    }

    @Override
    public int insertSelective(keywordanalysis record) {
        return 0;
    }

    @Override
    public keywordanalysis selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(keywordanalysis record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(keywordanalysis record) {
        return 0;
    }

    @Override
    public List<keywordanalysis> getTopKeywordByYear(String year) {
        return keywordanalysisMapper.getTopKeywordByYear(year);
    }

    @Override
    public List<keywordanalysis> getTopKeywordByType(String type) {
        return keywordanalysisMapper.getTopKeywordByType(type);
    }


}
