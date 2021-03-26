package com.geiyepa.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geiyepa.demo.bean.paper;
import com.geiyepa.demo.service.paperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/", produces = "application/json; charset=utf-8")
public class paperController {

    @Autowired
    private paperService paperService;

    @ResponseBody
    @RequestMapping(value = "/searchPapers" , method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONArray searchPapers(@RequestBody String JSONBody){

        JSONObject object = JSONObject.parseObject(JSONBody);
        String searchWord = (String) object.get("searchWord");
        List<paper> paperList = paperService.selectLikeWord("%"+searchWord+"%");
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(paperList));
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date) + " ====> 搜索文章 ##搜索关键词：" + searchWord + "  ##搜索结果数：" + paperList.size());

        return array;

    }
}
