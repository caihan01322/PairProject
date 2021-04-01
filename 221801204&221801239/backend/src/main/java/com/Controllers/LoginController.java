package com.Controllers;

import com.Services.ILoginService;
import com.Services.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController
{
    @Autowired
    private ILoginService loginService;

    @RequestMapping("/login")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public int Login(String username,String password)
    {
        return loginService.Login(username, password);
    }


}
