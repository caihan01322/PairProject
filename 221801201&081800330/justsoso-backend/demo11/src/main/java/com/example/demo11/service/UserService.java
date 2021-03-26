package com.example.demo11.service;

import com.example.demo11.model.User;
import com.sun.el.parser.BooleanNode;

public interface UserService
{
    User getUserByAccount(String account,String Password);
    boolean Register(String Account,String Password);
    int getUserByAccount(String account);
    String saveImg(String username,String imgUrl);
    String getImg(String account);
}
