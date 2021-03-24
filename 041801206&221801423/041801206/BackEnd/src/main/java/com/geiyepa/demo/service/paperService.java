package com.geiyepa.demo.service;

import com.geiyepa.demo.bean.paperWithBLOBs;
import com.geiyepa.demo.mapper.paperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Paper;
import java.util.ArrayList;

@Service
public class paperService {

    @Autowired
    public paperMapper paperMapper;

    public ArrayList<Paper>  getPapersByword(String word){
      ArrayList<Paper> paperArrayList=paperMapper.selectLikeWord("study");
        return paperArrayList;
    }

    public paperWithBLOBs getPaperById(Integer id){

        System.out.println("===>查询id："+id);
        paperWithBLOBs paperWithBLOBsr=paperMapper.selectByPrimaryKey(id);
        System.out.println(paperWithBLOBsr.toString());
        return paperWithBLOBsr;
    }


}
