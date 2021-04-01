package com.Controllers;

import com.Services.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController
{
    @Autowired
    private IRegisterService registerService;

    @RequestMapping("/register")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public int Register(String username,String password)
    {
        return registerService.Register(username,password);
    }

}
