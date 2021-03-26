package com.example.demo.dao;

import com.example.demo.pojo.Keyword;
import com.example.demo.service.PaperService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.Paper;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class PaperDaoTest {
    @Autowired
    PaperDao dao;

    @Autowired
    PaperService paperService;

    @Test
    public void testList(){
        List<Paper> list = dao.queryByKeyword("image");
        list.stream();
    }

    @Test
    public void testLimit(){
        Keyword keyword = new Keyword();
        keyword.setKeyword("image");
        List<Paper> papers = paperService.queryByKeywordLimit(keyword, 0, 10);
        papers.stream().forEach(System.out::println);
    }

    @Test
    public void testQuery(){
        Paper param = new Paper();
        param.setTitle("image");
        param.setMeeting("CVPR");
        param.setYear("2020");
        List<Paper> query = dao.query(param);
        query.stream().forEach(System.out::println);
    }

    @Test
    public void testQueryTitleLimit(){
        List<Paper> query = paperService.queryByTitleLimit("image",0, 10);
        query.stream().forEach(System.out::println);

    }

    @Test
    public void testQueryLimit(){
        Paper param = new Paper();
        param.setTitle("image");
        param.setMeeting("CVPR");
        param.setYear("2020");
        List<Paper> query = paperService.query(param,90,10);
        query.stream().forEach(System.out::println);
    }
    @Test
    public void testQueryNum(){
        Paper param = new Paper();
        param.setTitle("image");
        param.setMeeting("CVPR");
        param.setYear("2020");
        int num = dao.queryRezultNum(param);
        System.out.println(num);
    }

    @Test
    public void testQueryNum_by_keyword(){
        Keyword keyword = new Keyword();
        keyword.setKeyword("Rolling");
        int num = dao.queryByKeywordRezultNum(keyword);
        System.out.println(num);
    }
}
