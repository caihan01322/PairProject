package com.topwordanalysis.controller;


import com.topwordanalysis.databaseOperation.model.User;
import com.topwordanalysis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 221801318_黄贸之
 * @Date 2021/3/26
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user){
        String result = userService.login(user);
        return result;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user){
        String result = userService.sign(user);
        return result;
    }


}
