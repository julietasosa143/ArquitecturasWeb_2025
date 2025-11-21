package com.example.demo.service;

import com.example.demo.security.TokenContext;
import com.example.demo.service.llm.GroqClient;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IaChatService {

    private final TokenContext tokenContext;
    private final GroqClient groqClient;

    public Object processChat(String request, String token){
        tokenContext.setToken(token);
        return groqClient.preguntar(request);
    }

}
