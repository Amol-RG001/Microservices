package com.amol.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//No need to mark @EnableEurekaClient since it's a spring-boot 3.x
//itself consider eureka client as we added dependencies
@SpringBootApplication
public class UserServiceApplication {

	// Note:- we can do it in main class,
	// but it's better to do make separate package (config)

	//	@Bean
	//	public RestTemplate restTemplate(){
	//		return new RestTemplate();
	//	}

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
