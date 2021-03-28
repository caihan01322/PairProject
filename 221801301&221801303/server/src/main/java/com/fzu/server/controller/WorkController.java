package com.fzu.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.fzu.server.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class WorkController {
    @Autowired
    WorkService ws;
    @RequestMapping("/query")
    @ResponseBody
    public Object query(@RequestBody Map<String,Object> name){
        return ws.query(name);

    }
}
