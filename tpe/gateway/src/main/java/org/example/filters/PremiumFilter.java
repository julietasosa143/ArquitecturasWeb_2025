package org.example.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.feignClient.UsuarioFeignClient;
import org.example.security.jwt.TokenProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
@RequiredArgsConstructor
public class PremiumFilter extends OncePerRequestFilter {


    private final TokenProvider tokenProvider;        // para obtener email del JWT
    private final UsuarioFeignClient usuarioClient;   // para llamar /esPremium

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path.startsWith("/api/iachat")) {

            String authHeader = request.getHeader("Authorization");
            if (authHeader == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            String email = tokenProvider.getUsernameFromToken(authHeader.replace("Bearer ", ""));

            boolean premium = usuarioClient.esPremium(email).get("premium");

            if (!premium) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
