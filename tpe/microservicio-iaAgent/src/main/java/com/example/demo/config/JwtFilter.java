package com.example.demo.config;

import com.example.demo.dto.JwtError;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtFilter extends OncePerRequestFilter {


    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                // --- Extraer datos del token sin validar firma ---
                // Suponemos formato JWT estándar: header.payload.signature
                String[] parts = token.split("\\.");
                if (parts.length != 3) {
                    throw new RuntimeException("Token JWT inválido");
                }
                // payload está en base64
                String payloadJson = new String(java.util.Base64.getUrlDecoder().decode(parts[1]));

                // parseamos roles y usuario desde el payload
                com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                java.util.Map<String, Object> payloadMap = mapper.readValue(payloadJson, java.util.Map.class);

                String email = (String) payloadMap.get("sub"); // normalmente el email
                String auths = (String) payloadMap.getOrDefault("auth", "ROLE_USER"); // roles
                List<GrantedAuthority> authorities = Arrays.stream(auths.split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                // seteamos autenticación en SecurityContext
                Authentication auth = new UsernamePasswordAuthenticationToken(email, token, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                // token inválido
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        // pasamos al siguiente filtro
        filterChain.doFilter(request, response);
    }
}
