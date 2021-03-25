package com.geiyepa.demo.service;

import com.geiyepa.demo.bean.paper;
import com.geiyepa.demo.bean.paperWithBLOBs;
import com.geiyepa.demo.mapper.paperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service

public class paperService implements paperMapper{

    @Autowired
    private paperMapper paperMapper;




//    public paperWithBLOBs getPaperById(Integer id){
//        System.out.println("===>查询id："+id);
//        paperWithBLOBs paperWithBLOBsr=paperMapper.selectByPrimaryKey(id);
//        System.out.println(paperWithBLOBsr.toString());
//        return paperWithBLOBsr;
//    }
//
//    public Paper getById(Integer id){
//        return  paperMapper.getPaper(id);
//    }


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
        System.out.println("===>查询id："+id);
        paperWithBLOBs paperWithBLOBsr=paperMapper.selectByPrimaryKey(id);
        System.out.println(paperWithBLOBsr.toString());
        return paperWithBLOBsr;

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
    public paper getPaper(Integer id) {
        return null;
    }


}
