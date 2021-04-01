package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Dao.RegisterDaoImpl;
import Entity.LoginReturn;

@Controller

public class RegisterController {
    
    @RequestMapping(value="/register")
    @ResponseBody
    public LoginReturn userRegister(String username,String pwd) {
        RegisterDaoImpl re = new RegisterDaoImpl();
        LoginReturn loginReturn = re.userRegister(username, pwd);
        return loginReturn;
    }
}
