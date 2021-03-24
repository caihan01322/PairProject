package com.practice.pairproject.service.impl;

import com.practice.pairproject.dao.UserMapper;
import com.practice.pairproject.pojo.User;
import com.practice.pairproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfo(Integer uid) {
        return this.userMapper.selectByPrimaryKey(uid);
    }
}
