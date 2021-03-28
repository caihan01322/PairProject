package com.practice.pairproject.service.impl;

//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.plugins.Page;
import com.practice.pairproject.dao.KeywordMapper;
import com.practice.pairproject.dao.PaperMapper;
import com.practice.pairproject.pojo.Paper;
import com.practice.pairproject.service.PaperService;
import com.practice.pairproject.util.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public Page<Paper> searchPaper(MyPage<Paper> page, Map<String, String> paramMap) {
        //String keyword =  paramMap.get("keyword");
        //List<Integer> pidList= keywordMapper.searchKeywords(keyword);
        page.setRecords(paperMapper.searchPaper(page, paramMap));
        page.setMyPage();
        return page;
    }


    @Override
    public Page<Paper> selectAll(MyPage<Paper> page) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题
        // page.setOptimizeCountSql(false);
        // 不查询总记录数
        // page.setSearchCount(false);
        // 注意！！ 分页 total 是经过插件自动 回写 到传入 page 对象
        //return paperMapper.selectAll(page);
        page.setRecords(paperMapper.selectAll(page));
        page.setMyPage();
        return page;
    }

    @Override
    public Page<Paper> selectPaperByKeyword(MyPage<Paper> page, String keyword) {
        //查询拥有此keyword的所有pid的list
        List<Integer> pids = keywordMapper.searchKeywords(keyword);
        System.out.println("【pids】" + pids);
        page.setRecords(paperMapper.selelctByIDlist(page, pids));
        page.setMyPage();
        return page;
    }

}
