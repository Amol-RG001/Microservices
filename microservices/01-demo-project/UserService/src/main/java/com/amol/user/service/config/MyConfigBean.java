package com.amol.user.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfigBean {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
