package com.example.demo.controller;

import com.example.demo.pojo.Keyword;
import com.example.demo.pojo.Pager;
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
public class PagerControllerTest {

    @Autowired
    PagerController pagerController;

    @Autowired
    HotwordController hotwordController;


    @Test
    public void request_Paper_Query(){
        Paper param = new Paper();
        param.setTitle("image");
        param.setMeeting("iccv");
        Pager pager = pagerController.query(param, 1);
        System.out.println("fine");
    }

    @Test
    public void request_Paper_Query_Hotword(){
        Keyword param = new Keyword();
        param.setKeyword("image");
        param.setMeeting("cvpr");
        Pager pager = pagerController.hotwordRelatedPaper(param,139);
        System.out.println("fine");
    }

    @Test
    public void request_hotword(){
        List<Keyword> hotword = hotwordController.hotwords("cvpr");
        System.out.println("fine");
    }

    @Test
    public void request_hotword_trend(){
        Keyword param = new Keyword();
        param.setKeyword("image");
        //param.setMeeting("cvpr");
        List<Keyword> hotword = hotwordController.hotwordTreand(param);
        System.out.println("fine");
    }
}
