package org.example.microserviciouser.service;


import org.example.microserviciouser.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.example.microserviciouser.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    RestTemplate restTemplate;

    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }
    public Usuario getById(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario){
        Usuario nuevo= usuarioRepository.save(usuario);
        return nuevo;
    }

}
