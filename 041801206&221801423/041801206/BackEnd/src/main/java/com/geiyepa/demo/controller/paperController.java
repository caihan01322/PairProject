package com.geiyepa.demo.controller;

import com.geiyepa.demo.bean.paperWithBLOBs;
import com.geiyepa.demo.service.paperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Paper;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class paperController {

    @Autowired
    private paperService paperService;

    @ResponseBody
    @RequestMapping(value = "/hello")
    public String hello(){

//        paperService paperService = new paperService();
        List<Paper> paperArrayList=paperService.selectLikeWord("%Image%");
        Integer integer=paperArrayList.size();
        String num=integer.toString();
        return num;
//
//        paperWithBLOBs paperWithBLOBs=paperService.selectByPrimaryKey(1);
//        return paperWithBLOBs.toString();

//        return "123";


    }
}
