package com.pairwork.pairwork.service;


import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "user")
@Entity
public class User {
    private int id;
    private String password;

}
