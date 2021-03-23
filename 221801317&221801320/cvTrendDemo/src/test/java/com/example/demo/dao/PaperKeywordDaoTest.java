package com.example.demo.dao;

import com.example.demo.pojo.Keyword;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class PaperKeywordDaoTest {

    @Autowired
    PaperKeywordDao paperKeywordDao;
    @Test
    public void testgetHotWordResentYear(){
        List<Keyword> hotWordResentYear = paperKeywordDao.getHotWordResentYear();
        hotWordResentYear.stream().forEach(keyword -> {
            System.out.println(keyword.getKeyword()+": "+keyword.getFrequency());
        });
    }

}
