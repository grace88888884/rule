package com.yy.testruleonline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.yy.testruleonline")
@MapperScan("com.yy.testruleonline.mapper")
public class TestruleOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestruleOnlineApplication.class, args);
	}

}
