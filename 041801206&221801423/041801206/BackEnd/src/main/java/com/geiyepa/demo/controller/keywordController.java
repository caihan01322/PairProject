package com.geiyepa.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geiyepa.demo.bean.keywordanalysis;
import com.geiyepa.demo.bean.paper;
import com.geiyepa.demo.service.keywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/", produces = "application/json; charset=utf-8")
public class keywordController {
    @Autowired
    private keywordService keywordService;

    @ResponseBody
    @RequestMapping(value = "/getTopkeywordsByYear" , method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONArray getTopkeywordsByYear(@RequestBody String JSONBody){
        System.out.println(JSONBody);
        JSONObject object = JSONObject.parseObject(JSONBody);
        String searchYear = (String) object.get("year");System.out.println(searchYear);
        List<keywordanalysis> analysisList = keywordService.getTopKeywordByYear(searchYear);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(analysisList));

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date) + " ====> 搜索热词 ##按年份：" + searchYear + "  ##搜索结果数：" + analysisList.size());

        return array;
    }

    @ResponseBody
    @RequestMapping(value = "/getTopkeywordsByType" , method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONArray getTopkeywordsByType(@RequestBody String JSONBody){
        System.out.println(JSONBody);
        JSONObject object = JSONObject.parseObject(JSONBody);
        String searchType = (String) object.get("type");System.out.println(searchType);
        List<keywordanalysis> analysisList = keywordService.getTopKeywordByType(searchType);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(analysisList));

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date) + " ====> 搜索热词 ##按类型：" + searchType + "  ##搜索结果数：" + analysisList.size());

        return array;
    }

}
