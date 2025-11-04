package org.example.microserviciouser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

    @Configuration // 1. Le dice a Spring que esta clase define beans
    public class RestTemplateConfig {

        @Bean // 2. Le dice a Spring que registre el objeto devuelto por este método
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }
