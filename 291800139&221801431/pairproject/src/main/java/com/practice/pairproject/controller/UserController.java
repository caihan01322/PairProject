package com.practice.pairproject.controller;

import com.practice.pairproject.pojo.User;
import com.practice.pairproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/user"})
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = {"getUser/{uid}"}, method = {RequestMethod.GET})
    public User GetUser(@PathVariable String uid) {
        System.out.println("[GET]");
        User temp = userService.getUserInfo(Integer.parseInt(uid));
        return temp;
    }

    @RequestMapping(value = {"/login"})
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/register"})
    public String register() {
        return "register";
    }
}
