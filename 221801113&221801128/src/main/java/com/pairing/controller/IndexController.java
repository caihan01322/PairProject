package com.pairing.controller;

import com.pairing.bean.User;
import com.pairing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    UserService userService;
    /**
     * 返回登录页面
     * @return
     */
    @GetMapping(value = {"/", "/login"})
    public String loginPage() {
        return "login";
    }


    @GetMapping(value = "/register")
    public String registerPage() {
        return "login";
    }

    /**
     * 登录
     * @param user
     * @param session
     * @param model
     * @return
     */
    @PostMapping("/login")
    public String judLogin(User user, HttpSession session, Model model) {
        if (userService.isMember(user.getUserName().trim(), user.getPassword().trim())) {
            session.setAttribute("loginUser", user);
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg","用户名或密码错误！");
            return "login";
        }
    }

    @PostMapping("/register")
    public String judRegister(User user, HttpSession session, Model model) {
        if (user.getUserName().trim().equals("") || user.getPassword().trim().equals("")) {
            model.addAttribute("msg","注册失败，用户名或密码不能为空！");
            return "login";
        } else if (userService.register(user.getUserName().trim(), user.getPassword().trim())) {
            session.setAttribute("loginUser", user);
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg","注册失败(可能原因：用户名已被使用)");
            return "login";
        }
    }

    /**
     * 返回main页面
     * @return
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session, Model model) {
        return "main";
    }

    @GetMapping("/log_off")
    public String logOff(HttpSession session) {
        session.removeAttribute("loginUser");
        return "/";
    }
}
