package com.pair.controller.user;

import com.pair.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 用户主页
 */
@Controller
@RequestMapping("/user")
public class UserIndexController {

    @Resource
    private UserService userService;

    /**
     * 转向主页
     */
    @RequestMapping("/index")
    public String index() {
        return "user/index";
    }

}
