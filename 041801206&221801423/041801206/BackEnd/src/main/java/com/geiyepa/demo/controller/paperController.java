package com.geiyepa.demo.controller;

import com.geiyepa.demo.bean.paperWithBLOBs;
import com.geiyepa.demo.service.paperService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Paper;
import java.util.ArrayList;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/")
public class paperController {

    @ResponseBody
    @RequestMapping(value = "/hello")
    public String hello(){

        paperService paperService = new paperService();
//        ArrayList<Paper> paperArrayList=paperService.getPapersByword("word");
//        return paperArrayList.get(1).toString();

        paperWithBLOBs paperWithBLOBs=paperService.getPaperById(1);
        return paperWithBLOBs.toString();


    }
}
