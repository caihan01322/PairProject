package com.fzu.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fzu.server.dao.PaperDao;
import com.fzu.server.pojo.Paper;
import com.fzu.server.service.InputJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;
import java.util.Map;


@SpringBootApplication
public class ServerApplication {
    @Autowired
    PaperDao dao;
    @Autowired
    InputJson inj;

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);

    }

    @PostConstruct
    public void setJSON() throws IOException {
        inj.getECCVJson();
        inj.getCVPRJson();
        inj.getICCVJson();
//        List<Paper> paper = dao.getPaper("IEEE/CVF Conference on Computer Vision and Pattern Recognition");
//        System.out.println("wait");
    }





}
