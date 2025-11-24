package com.example.demo.config;

import com.example.demo.security.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiConfig {

    @Bean
    public RestTemplate restTemplate(TokenProvider tokenProvider) {
        RestTemplate rest = new RestTemplate();

        rest.getInterceptors().add((request, body, execution) -> {
            String token = tokenProvider.getCurrentToken();  // ← token desde SecurityContext
            if (token != null) {
                request.getHeaders().set("Authorization", "Bearer " + token);
            }
            return execution.execute(request, body);
        });

        return rest;
    }
}
