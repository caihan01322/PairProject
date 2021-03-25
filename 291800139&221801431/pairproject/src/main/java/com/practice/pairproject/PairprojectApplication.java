package com.practice.pairproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan({"com.practice.pairproject.dao"})
@SpringBootApplication
public class PairprojectApplication {

    public static void main(String[] args) {

        SpringApplication.run(PairprojectApplication.class, args);
    }

}
