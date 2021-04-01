package com.pair.service;


import com.pair.model.role.User;

public interface UserService {
    /**
     * 用户登录
     */
    public User login(String name, String password);
}
