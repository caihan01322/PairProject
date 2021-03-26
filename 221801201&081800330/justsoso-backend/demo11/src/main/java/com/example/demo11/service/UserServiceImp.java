package com.example.demo11.service;

import com.example.demo11.dao.UserDaoImpl;
import com.example.demo11.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService
{
    @Autowired
    private UserDaoImpl userDao;
    @Override
    public User getUserByAccount(String account,String Password)
    {
        return userDao.getUserByAccount(account,Password);
    }
    @Override

    public boolean Register(String Account,String Password)
    {
        return userDao.Register(Account,Password);
    }
    @Override
     public int getUserByAccount(String account)
    {
        return userDao.getUserByAccount(account);
    }
    @Override
    public String saveImg(String username,String imgUrl)
    {
        return userDao.saveImg(username,imgUrl);
    }
    @Override
    public String getImg(String account)
    {
        return userDao.getImg(account);
    }
}
