package com.pair.controller;

import javax.annotation.Resource;

import cn.hutool.db.Session;
import com.pair.model.role.User;
import com.pair.service.UserService;
import com.pair.session.SessionContainer;
import com.pair.util.DataUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * 用户登录
 */
@Controller
public class LoginController {

	@Resource
	private UserService userService;

	/**
	 * 转到登录页面
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * 真正的登录
	 */
	@RequestMapping(value = "/login/do")
	public String doLogin(String username, String password, String verify, Model model, HttpServletRequest request) {

		if (!DataUtil.isValid(username, password)) {
			//提示信息
			model.addAttribute("error","用户名密码不能为空");
			//跳转页面
			return "login";
		} else if(!DataUtil.checkVerify(verify,request.getSession())){
			//验证码不正确
			//提示信息
			model.addAttribute("error","验证码错误");
			//跳转页面
			return "login";
		} else {
			User user = userService.login(username, password);
			if(user != null) {
				//登陆成功
				String id = String.valueOf(user.getId());
				HttpSession session = request.getSession();
				SessionContainer.loginUsers.put(String.valueOf(id), session);
				request.getSession().setAttribute("user",user);
				return "redirect:/user/index";
			} else{
				//登陆失败
				model.addAttribute("error","账号或密码不正确");
				//跳转页面
				return "login";
			}
		}
	}


}
