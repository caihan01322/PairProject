package com.pairing.service;

import com.pairing.bean.User;
import com.pairing.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 判断是否注册
     * @param userName
     * @param password
     * @return
     */
    public Boolean isMember(String userName, String password) {
       User user = userMapper.isMember(userName);
       if (user != null && user.getPassword().equals(password)) {
            return true;
       }
       return false;
    }

    /**
     * 注册
     * @param userName
     * @param password
     * @return
     */
    public Boolean register(String userName, String password) {
        Integer integer = new Integer(0);
        try{
            integer = userMapper.register(userName, password);
        } catch (Exception e) {
            integer = new Integer(0);
        }
        if (integer == null) integer = new Integer(0);
        return (integer.intValue() == 0) ? false : true;
    }
}
