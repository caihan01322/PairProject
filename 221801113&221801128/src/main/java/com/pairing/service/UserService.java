package com.pairing.service;

import com.pairing.bean.User;
import com.pairing.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User getUserById(String userName) {
       /*if (userMapper.getUserById(userName) != null && ) {

       }*/
       return userMapper.getUserById(userName);
    }
}
