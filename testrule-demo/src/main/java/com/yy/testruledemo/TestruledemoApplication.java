package com.yy.testruledemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.yy")
@MapperScan("com.yy")
public class TestruledemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestruledemoApplication.class, args);
    }

}
