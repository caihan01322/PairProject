package com.eepractice.webcrawller.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户认证数据类型
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class AuthResponse {
    private Integer userId  = 0;
    private String username  = "";
    private Integer statusCode  = 0;
    private String errMsg  = "";
    private String token = "";

    public AuthResponse(Integer statusCode , String errMsg){
        this.statusCode = statusCode;
        this.errMsg = errMsg;
    }

    public AuthResponse(Integer userId , String username , Integer statusCode){
        this.userId = userId;
        this.username = username;
        this.statusCode = statusCode;
    }
}
