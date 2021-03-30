package com.fzu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication()
//@PropertySource("application.yml")
public class PaperSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaperSearchApplication.class, args);
        /*System.out.println("上传完成!!!");*/
    }

}
