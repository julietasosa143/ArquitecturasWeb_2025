package com.example.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {
    public String getCurrentToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) return null;

        // Si el token está en credentials
        if (authentication.getCredentials() instanceof String token) {
            return token;
        }

        // Alternativa si lo guardaste en principal
        if (authentication.getPrincipal() instanceof String token2) {
            return token2;
        }

        return null;
    }
}
