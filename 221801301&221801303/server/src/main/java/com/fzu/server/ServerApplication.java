package com.fzu.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fzu.server.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ServerApplication {

    @Autowired
    UserDao dao;
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);

    }
    @PostConstruct
    public void showName(){
        System.out.println(dao.getName(1));
        JSONObject parse = JSON.parse();
        parse.ge
    }

}
