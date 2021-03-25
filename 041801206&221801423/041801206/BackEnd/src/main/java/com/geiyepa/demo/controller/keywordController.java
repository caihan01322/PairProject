package com.geiyepa.demo.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/", produces = "application/json; charset=utf-8")
public class keywordController {



    public JSONArray getTopkeywords()

}
