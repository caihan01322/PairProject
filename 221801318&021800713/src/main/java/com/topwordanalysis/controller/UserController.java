package com.topwordanalysis.controller;


import com.topwordanalysis.databaseOperation.model.User;
import com.topwordanalysis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 221801318_黄贸之
 * @Date 2021/3/26
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/login")
    public String login(String mail,String password){
        User user = new User(mail,password);
        String result = userService.login(user);
        return result;
    }

}
