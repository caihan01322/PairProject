package com.pair.intercepter;

import com.pair.model.role.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**登录拦截器
 * */
public class LoginIntercepter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        String contextPath = request.getContextPath();
        HttpSession session = request.getSession();
        //如果是去向用户页面
        if (path.indexOf("user") != -1) {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect(contextPath + "/login");
                return false;
            } else if (session.getAttribute("force") != null) {
            	/*//此账号已在其它地方登录
            	response.sendRedirect(contextPath + "/valid");
            	session.invalidate();*/
                return false;
            }
            return true;
        }
        return true;
    }
}

