package com.example.demo.dao;

import com.example.demo.pojo.Keyword;
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

    @Test
    public void testList(){
        Keyword keyword = new Keyword();
        keyword.setKeyword("image");
        List<Paper> list = dao.query(keyword);
        list.stream();
    }
}
