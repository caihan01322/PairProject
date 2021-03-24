package com.topwordanalysis.databaseOperation.model;

import lombok.AllArgsConstructor;

/**
 * User类
 *
 * @author 221801318_黄贸之
 * @Date 2021/3/23
 */

@AllArgsConstructor
public class User {
    private String mail;
    private String password;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
