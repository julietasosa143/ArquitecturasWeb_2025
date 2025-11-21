package org.example.config;

import org.example.filters.PremiumFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PremiumFilterConfig {
    @Bean
public FilterRegistrationBean<PremiumFilter> premiumFilterRegistration(PremiumFilter filter) {
    FilterRegistrationBean<PremiumFilter> registration = new FilterRegistrationBean<>();
    registration.setFilter(filter);

    // Aplica el filtro SOLO a /api/iachat/**
    registration.addUrlPatterns("/api/iachat/*");

    registration.setOrder(2); // después del JwtFilter

    return registration;
}
}