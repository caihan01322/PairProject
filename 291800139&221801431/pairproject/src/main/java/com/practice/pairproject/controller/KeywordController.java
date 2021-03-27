package com.practice.pairproject.controller;

import com.practice.pairproject.pojo.User;
import com.practice.pairproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/keyword"})
public class KeywordController {

    @Autowired
    private UserService userService;

    //@ResponseBody
    @GetMapping(value = {"paperlist/{keyword}"})
    public User GetUser(@PathVariable String uid) {
        User temp = userService.getUserInfo(Integer.parseInt(uid));
        return temp;
    }
}
