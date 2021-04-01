package com.eepractice.webcrawller.controller;

import com.eepractice.webcrawller.bean.AuthResponse;
import com.eepractice.webcrawller.bean.RegisterUser;
import com.eepractice.webcrawller.bean.User;
import com.eepractice.webcrawller.context.UserContext;
import com.eepractice.webcrawller.enums.AuthCodeEnum;
import com.eepractice.webcrawller.repository.UserRepository;
import com.eepractice.webcrawller.service.AuthService;
import com.eepractice.webcrawller.utils.CommonUtils;
import org.apache.tomcat.util.descriptor.web.ContextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/auth")
public class AuthController {

    static final int OK = 200;
    static final int P1P2CODE = 201;
    static final int EMP_CONTENT = 202;
    static final int USER_EXISTS = 203;

    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommonUtils commonUtils;

    private boolean isUserDFormValid(User user){
        return !user.getUsername().trim().equals("") && !user.getPassword().trim().equals("");
    }


    @PostMapping(path = "/register",produces = "application/json")
    public ResponseEntity<Map<String,Object>> handleRegister(@RequestBody RegisterUser registerUser){
        Map<String,Object> responseMap = new HashMap<>();
        AuthCodeEnum registerResult = authService.register(registerUser);
        switch (registerResult){
            case P1_AND_P2_NOT_EQUAL:
                responseMap.put("data",new AuthResponse(P1P2CODE,"两次密码不一致！"));
                break;
            case EMPTY_INPUT:
                responseMap.put("data",new AuthResponse(EMP_CONTENT,"输入为空！"));
                break;
            case USERNAME_EXISTS:
                responseMap.put("data",new AuthResponse(USER_EXISTS,"用户名已存在！"));
                break;
            case OK:
                User registeredUser = authService.getRegisteredUser();
                responseMap.put("data",
                        new AuthResponse(registeredUser.getId(),
                                registeredUser.getUsername(),
                                OK));
                break;
            default:break;
        }
        return ResponseEntity.ok(responseMap);
    }

    @PostMapping(value = "/login",produces = "application/json")
    public ResponseEntity<Map<String,Object>> handleLogin(@RequestBody User user){
        if (!isUserDFormValid(user)){
            return new ResponseEntity<>(commonUtils.generateSimpleMap("message","登录信息错误"),HttpStatus.NOT_FOUND);
        }
        AuthResponse authResponse = authService.login(user);
        // 将用户添加到上下文中
        UserContext.setCurrentUser(authResponse.getUsername());
        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("data",authResponse);
        return ResponseEntity.ok(responseMap);
    }

    /**
     * 用户登出，清空用户上下文信息
     * @return
     */
    @DeleteMapping(value = "/logout",produces = "application/json")
    public ResponseEntity<?> logout(){
        UserContext.resetCurrentUser();
        Map<String,Object> responseMap = new HashMap<>();
        return  ResponseEntity.ok(responseMap);
    }
}
