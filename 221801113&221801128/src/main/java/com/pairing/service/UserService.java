package com.pairing.service;

import com.pairing.bean.User;
import com.pairing.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public Boolean getUser(String userName, String password) {
       User user = userMapper.getUser(userName);
       if (user != null && user.getPassword().equals(password)) {
            return true;
       }
       return false;
    }
}
