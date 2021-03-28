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
    @RequestMapping("/queryName")
    @ResponseBody
    public Object queryName(@RequestBody Map<String,Object> name){
        return ws.queryName(name);
    }

    @RequestMapping("/queryAuthor")
    @ResponseBody
    public Object queryAuthor(@RequestBody Map<String,Object> name){
        return ws.queryAuthor(name);
    }

    @RequestMapping("/getCVPR")
    @ResponseBody
    public Object getCVPR(){
        return ws.getCVPR();
    }

    @RequestMapping("/getECCV")
    @ResponseBody
    public Object getECCV(){
        return ws.getECCV();
    }

    @RequestMapping("/getICCV")
    @ResponseBody
    public Object getICCV(){
        return ws.getICCV();
    }
}
