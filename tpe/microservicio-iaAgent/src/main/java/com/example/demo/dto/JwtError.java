package com.example.demo.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

public class JwtError {
    private final String message = "Token expired";
    private final String date = LocalDateTime.now().toString();

    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            return "{ \"message\": \"" + message + "\" }";
        }
    }
}
