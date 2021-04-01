package com.Services.impl;

import com.Services.ILoginService;
import com.mapper.LoginMapper;
import com.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService implements ILoginService
{
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public int Login(String username,String password)
    {
        if (loginMapper.getUserByName(username) == null)//用户名在数据库中不存在
        {
            return -1;
        }
        else if (!password.equals(loginMapper.getPassword(username)))//密码不匹配
        {
            return -1;
        }
        else return 1;
    }
}
