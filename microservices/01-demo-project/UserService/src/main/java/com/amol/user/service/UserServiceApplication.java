package com.amol.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//No need to mark @EnableEurekaClient since it's a spring-boot 3.x
//itself consider eureka client as we added dependencies
@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
