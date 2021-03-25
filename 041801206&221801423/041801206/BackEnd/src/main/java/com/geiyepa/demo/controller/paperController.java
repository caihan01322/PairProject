package com.geiyepa.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.geiyepa.demo.bean.paper;
import com.geiyepa.demo.bean.paperWithBLOBs;
import com.geiyepa.demo.service.paperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class paperController {

    @Autowired
    private paperService paperService;

    @ResponseBody
    @RequestMapping(value = "/searchPapers" , method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONArray searchPapers(String searchWord){

        System.out.println("获取的参数："+searchWord);
                    List<paper> paperList = paperService.selectLikeWord("%"+searchWord+"%");
//        List<paper> paperList = paperService.selectLikeWord("%"+searchWord+"%");
                     JSONArray array= JSONArray.parseArray(JSON.toJSONString(paperList));
        System.out.println("List结果数量："+paperList.size());
        System.out.println("JsonarrayList结果数量："+array.size());
//        JSONArray papers = new JSONArray();
//        papers=paperList;

        return array;
//
//        paperWithBLOBs paperWithBLOBs=paperService.selectByPrimaryKey(1);
//        return paperWithBLOBs.toString();

//        return "123";


    }
}
