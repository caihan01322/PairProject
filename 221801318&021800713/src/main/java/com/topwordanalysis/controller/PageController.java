package com.topwordanalysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: PageController
 * @Description: 测试
 * @author: 黄贸之
 * @date: 2021/3/28 15:36
 * @Github: https://github.com/h2333
 */

@Controller
@RequestMapping("/index")
public class PageController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/papersearch")
    public String paperSearch(){
        return "paperSearch";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/keyword")
    public String keywords(){
        return "keywords";
    }

    @RequestMapping("/paperlist")
    public String paperlist(){
        return "paperList";
    }

    @RequestMapping("/analyse")
    public String analyse(){
        return "analyse";
    }
}
