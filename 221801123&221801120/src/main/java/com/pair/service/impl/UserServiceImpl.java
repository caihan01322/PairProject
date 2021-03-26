package com.pair.service.impl;


import com.pair.dao.UserDao;
import com.pair.model.role.User;
import com.pair.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource(name = "userDao")
    private UserDao userDao;

    @Override
    public User login(String name, String password) {
        return null;
    }
}
