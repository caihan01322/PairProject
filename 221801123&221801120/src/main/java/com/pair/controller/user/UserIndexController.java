package com.pair.controller.user;

import com.pair.model.role.User;
import com.pair.service.UserService;
import com.pair.session.SessionContainer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    /**
     * 转向热门分析
     */
    @RequestMapping("/statistics")
    public String statistics() {
        return "user/statistics";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        SessionContainer.loginUsers.remove(String.valueOf(user.getId()));
        // 清除session中user
        session.invalidate();
        return "redirect:/login";
    }

    @RequestMapping("/mylove")
    public String myLove() {
        return "user/mylove";
    }

    @RequestMapping("/trend")
    public String trend() {
        return "user/trend";
    }
}
