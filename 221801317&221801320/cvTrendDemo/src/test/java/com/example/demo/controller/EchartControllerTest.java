package com.example.demo.controller;

import com.example.demo.pojo.EchartPojo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class EchartControllerTest {
    @Autowired
    ChartController chartController;
    @Test
    public void getHotwordTrends(){
        EchartPojo hotwordTrends = chartController.getHotwordTrends("");
        System.out.println("fine");
    }

}
