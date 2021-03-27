package com.practice.pairproject.service.impl;

import com.practice.pairproject.dao.KeywordMapper;
import com.practice.pairproject.dao.PaperMapper;
import com.practice.pairproject.pojo.Paper;
import com.practice.pairproject.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperMapper paperMapper;

    @Autowired
    private KeywordMapper keywordMapper;


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

    @Override
    public List<Paper> searchPaper(Map<String, String> paramMap) {
        //String keyword =  paramMap.get("keyword");
        //List<Integer> pidList= keywordMapper.searchKeywords(keyword);
        return paperMapper.searchPaper(paramMap);
    }


    @Override
    public List<Paper> selectAll() {
        return paperMapper.selectAll();
    }

    @Override
    public List<Paper> selectPaperByKeyword(String keyword) {
        //查询拥有此keyword的所有pid的list
        List<Integer> pids = keywordMapper.searchKeywords(keyword);
        System.out.println("【pids】" + pids);
        List<Paper> paperList = paperMapper.selelctByIDlist(pids);
        return paperList;
    }
}
