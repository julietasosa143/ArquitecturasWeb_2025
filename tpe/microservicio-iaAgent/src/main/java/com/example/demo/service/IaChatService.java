package com.example.demo.service;

import com.example.demo.security.TokenContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IaChatService {

    private final TokenContext tokenContext;
    private final GroqClient gropClient;

    public Object processChat(HttpServletRequest request, String token){
        tokenContext.setToken(token);
        return gropClient.handleChat(request);
    }

}
