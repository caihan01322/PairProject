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
    //通过文章标题查询(可以模糊查询)
    public Object queryName(@RequestBody Map<String,Object> req){
        return ws.queryName(req);
    }

    @RequestMapping(value = "/queryAuthor",method = {RequestMethod.POST})
    @ResponseBody
    //通过文章作者名查询(可以模糊查询)
    public Object queryAuthor(@RequestBody Map<String,Object> req){
        return ws.queryAuthor(req);
    }

    @RequestMapping(value = "/queryKeyword",method = {RequestMethod.POST})
    @ResponseBody
    //通过关键词查询(不支持模糊查询)
    public Object queryKeyword(@RequestBody Map<String,Object> req){
        return ws.queryKeyword(req);
    }

    @RequestMapping(value = "/getCVPR",method = {RequestMethod.GET})
    @ResponseBody
    //获取CVPR会议TOP10
    public Object getCVPR(){
        return ws.getCVPR();
    }

    @RequestMapping(value = "/getECCV",method = {RequestMethod.GET})
    @ResponseBody
    //获取ECCV会议TOP10
    public Object getECCV(){
        return ws.getECCV();
    }

    @RequestMapping(value = "/getICCV",method = {RequestMethod.GET})
    @ResponseBody
    //获取ICCV会议TOP10
    public Object getICCV(){
        return ws.getICCV();
    }

    @RequestMapping(value = "/getTOP",method = {RequestMethod.GET})
    @ResponseBody
    //获取所有会议TOP10
    public Object getTOP(){
        return ws.getTOP();
    }

    @RequestMapping(value = "/getDetail",method = {RequestMethod.POST})
    @ResponseBody
    //通过文章id查询
    public Object getDetail(@RequestBody Map<String,Object> req){
        return ws.getDetail(req);
    }


}
