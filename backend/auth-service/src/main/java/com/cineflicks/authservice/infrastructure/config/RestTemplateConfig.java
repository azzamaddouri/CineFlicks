package com.cineflicks.authservice.infrastructure.config;

import com.cineflicks.authservice.domain.exception.UserServiceResponseErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new UserServiceResponseErrorHandler());
        return restTemplate;
    }
}
