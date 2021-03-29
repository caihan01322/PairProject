package com.fzu.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.fzu.server.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin("*")
public class WorkController {
    @Autowired
    WorkService ws;
    @RequestMapping(value = "/queryName",method = {RequestMethod.POST})
    @ResponseBody
    public Object queryName(@RequestBody Map<String,Object> req){
        return ws.queryName(req);
    }

    @RequestMapping(value = "/queryAuthor",method = {RequestMethod.POST})
    @ResponseBody
    public Object queryAuthor(@RequestBody Map<String,Object> req){
        return ws.queryAuthor(req);
    }

    @RequestMapping(value = "/getCVPR",method = {RequestMethod.GET})
    @ResponseBody
    public Object getCVPR(){
        return ws.getCVPR();
    }

    @RequestMapping(value = "/getECCV",method = {RequestMethod.GET})
    @ResponseBody
    public Object getECCV(){
        return ws.getECCV();
    }

    @RequestMapping(value = "/getICCV",method = {RequestMethod.GET})
    @ResponseBody
    public Object getICCV(){
        return ws.getICCV();
    }
}
