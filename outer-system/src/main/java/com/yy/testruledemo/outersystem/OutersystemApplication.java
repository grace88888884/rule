package com.yy.testruledemo.outersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages="com.yy.testruledemo.outersystem")
public class OutersystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutersystemApplication.class, args);
	}

}
