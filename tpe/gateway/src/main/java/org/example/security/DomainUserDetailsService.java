package org.example.security;

import org.example.dto.UsuarioDTO;
import org.example.entity.Authority;
import org.example.entity.User;
import org.example.feignClient.UsuarioFeignClient;
import org.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UsuarioFeignClient usuarioFeignClient;

    public DomainUserDetailsService( UsuarioFeignClient userFeignClient ) {
        this.usuarioFeignClient = userFeignClient;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String email ) throws UsernameNotFoundException {
        log.debug("Authenticating {}", email);

        UsuarioDTO usuario = usuarioFeignClient.getUsuarioByEmail(email);
        System.out.println("🧩 Usuario recibido del microservicio user: " + usuario);
        if (usuario == null) {
            throw new UsernameNotFoundException("El usuario con email " + email + " no existe");
        }

        return createSpringSecurityUser(usuario);
    }

    private UserDetails createSpringSecurityUser(UsuarioDTO usuario) {
        if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            throw new IllegalArgumentException("El usuario no tiene una contraseña válida en la base de datos.");
        }

        String roleName = "ROLE_" + usuario.getRol().toUpperCase();
        List<GrantedAuthority> grantedAuthorities = Collections.singletonList(
                new SimpleGrantedAuthority(roleName)
        );


        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getPassword(),
                grantedAuthorities
        );
    }
}
