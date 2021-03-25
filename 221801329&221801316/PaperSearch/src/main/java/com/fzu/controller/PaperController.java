package com.fzu.controller;

import com.alibaba.fastjson.JSON;
import com.fzu.Util.Result;
import com.fzu.pojo.Paper;
import com.fzu.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class PaperController {
    @Autowired
    PaperService paperService;

    @ResponseBody
    @RequestMapping("/queryPage")
    public List<Paper>  queryByPage(){
        List<Paper> paperList=paperService.queryPaperByPage(0,10);
        return paperList;
    }

    @ResponseBody
    @RequestMapping("/queryByKeyword")
    public List<Paper> queryByKeyword(){
        List<Paper> paperList=paperService.queryPaperByKeyword("digital",0,10);
        return paperList;
    }

    @ResponseBody
    @RequestMapping("/queryByAuthor")
    public List<Paper> queryByAuthor(){
        List<Paper> paperList=paperService.queryPaperByAuthor("G.A. Giraldi",0,10);
        return paperList;
    }

    @ResponseBody
    @RequestMapping("/queryTop10ByYear")
    public String queryTop10ByYear(){
        String data=JSON.toJSONString(paperService.queryTop10ByYear());
        return  data;
    }


}
