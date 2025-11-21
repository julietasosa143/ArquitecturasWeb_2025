package com.example.demo.feign;

import com.example.demo.security.TokenContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor  implements RequestInterceptor {
    @Autowired
    private TokenContext tokenContext;

    public void apply(RequestTemplate requestTemplate) {
        String token= tokenContext.getToken();
        if(token!=null){//no estoy seguro pero capaz deberia inclir "Bearer" + token ) si e  q no me anda
            requestTemplate.header("Authorization", "Bearer " + token);
        }
    }
}
