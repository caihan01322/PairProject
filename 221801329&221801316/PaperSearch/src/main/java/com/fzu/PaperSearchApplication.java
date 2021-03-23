package com.fzu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.fzu.mapper")
@SpringBootApplication
public class PaperSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaperSearchApplication.class, args);
    }

}
