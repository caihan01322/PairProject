package com.eepractice.webcrawller.service;

import com.eepractice.webcrawller.bean.AuthResponse;
import com.eepractice.webcrawller.bean.RegisterUser;
import com.eepractice.webcrawller.bean.User;
import com.eepractice.webcrawller.enums.AuthCodeEnum;
import com.eepractice.webcrawller.repository.UserRepository;
import com.eepractice.webcrawller.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    private User registeredUser;

    public AuthCodeEnum register(RegisterUser registerUser){
        if (registerUser.hasEmptyInput()) return AuthCodeEnum.EMPTY_INPUT;
        if (!registerUser.isP1EqualsP2()) return AuthCodeEnum.P1_AND_P2_NOT_EQUAL;

        // 判断用户名是否已存在
        User user = userRepository.findUserByUsername(registerUser.getUsername());
        if (user != null) return AuthCodeEnum.USERNAME_EXISTS;
        // 用户名不存在，执行注册
        User userWaitForSave = new User();
        userWaitForSave.setUsername(registerUser.getUsername().trim());
        userWaitForSave.setPassword(registerUser.getPassword1());
        try{
            User savedUser = userRepository.save(userWaitForSave);
            registeredUser  = savedUser;
            return AuthCodeEnum.OK;
        }catch (Exception e){
            e.printStackTrace();
            return AuthCodeEnum.ERROR;
        }
    }

    public AuthResponse login(User user){
        String originPassword = user.getPassword();
        String targetPassword = CommonUtils.MD5Encode(originPassword);
        User userExists = userRepository.findUserByUsername(user.getUsername());
        AuthResponse authResponse = new AuthResponse();
        if (userExists == null || !userExists.getPassword().equals(targetPassword))
        {
            authResponse.setStatusCode(201).setErrMsg("用户名或密码错误！");
        }
        else{
            String userToken = CommonUtils.generateToken(userExists);
            return authResponse
                    .setUsername(userExists.getUsername())
                    .setUserId(userExists.getId())
                    .setStatusCode(200)
                    .setToken(userToken);
        }
        return null;
    }

    public User getRegisteredUser() {
        return registeredUser;
    }
}
