package com.example.projectz.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {
    //Instance object which will create by spring bean container later we can access them.
    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }
}
