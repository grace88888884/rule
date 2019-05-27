package com.yy.testruledemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.yy")
@MapperScan({"com.yy.testrule.dao.mapper", "com.yy.testruleonline.dao.mapper"})
public class TestruleOnlineApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TestruleOnlineApplication.class, args);
    }

}
