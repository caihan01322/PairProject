package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.UserDao;
import utils.DatabaseUtils;

@Controller
public class UserController {
    
    @RequestMapping("/user")
    public String login() {
        return "login";
    }
    
    @RequestMapping(value = "/user/success")
    @ResponseBody
    public boolean success(String username, String password) {
        UserDao dao = new UserDao();
        dao.setConnection(DatabaseUtils.connectToUser());
        boolean temp = dao.judge(username, password);
        return temp;
    }
}
