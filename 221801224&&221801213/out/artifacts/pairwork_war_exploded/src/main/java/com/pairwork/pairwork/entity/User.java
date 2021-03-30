package com.pairwork.pairwork.entity;


import javax.persistence.*;

@Table(name = "user")
@Entity

public class User {
    @Id//标识Id为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//标识主键为自动递增
    private Long id;
    private String password;
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
