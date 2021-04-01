package com.Services.impl;

import com.Services.IRegisterService;
import com.mapper.RegisterMapper;
import com.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService implements IRegisterService
{
    @Autowired
    private RegisterMapper registerMapper;

    @Override
    public int Register(String username, String password)
    {
        int userNum = registerMapper.getUserByName(username);
        if (userNum != 0 )//数据库内已经存在
        {
            return -1;
        }
        else registerMapper.addUser(username,password);
        return 1;
    }
}
