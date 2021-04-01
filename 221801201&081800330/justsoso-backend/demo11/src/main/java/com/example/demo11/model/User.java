package com.example.demo11.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable
{
    public String account;
    public String password;
    public String username;
    public String description;
    public User(String Account,String Password)
    {
        this.account = Account;
        this.password = Password;
    }
}
