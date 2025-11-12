package org.example.microserviciouser.service;


import org.example.microserviciouser.dto.UsuarioDTO;
import org.example.microserviciouser.entities.Usuario;
import org.example.microserviciouser.feignClient.MonopatinFeignClient;
import org.example.microserviciouser.feignClient.ParadaFeignClient;
import org.example.microserviciouser.feignClient.ViajeFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.example.microserviciouser.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private ViajeFeignClient viajeFeignClient;
    @Autowired
    private MonopatinFeignClient monopatinFeignClient;
    @Autowired
    private ParadaFeignClient paradaFeignClient;

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

    public List<UsuarioDTO> getUsuariosRecurrente(int mes, int anio, String tipoUsuario){
        System.out.println("👉 Entró al Service getUsuariosRecurrente()");
        List<UsuarioDTO> recurrentes= new ArrayList<>();
        List<Long> idRecurrentes= viajeFeignClient.getUsuariosRecurrentes(mes,anio);
        System.out.println("Feign devolvió IDs: " + idRecurrentes);
        for(Long id:idRecurrentes){
            System.out.println(" Buscando usuario ID: " + id);
            Usuario temporary = usuarioRepository.findById(id).orElse(null);
            if(temporary!=null){
                if(temporary.getRol().contains(tipoUsuario)){
                    recurrentes.add(new UsuarioDTO(temporary.getId(), temporary.getNombre(), temporary.getApellido(),temporary.getRol()));
                }
            }
        }
        System.out.println("🔚 Service devuelve " + recurrentes.size() + " usuarios recurrentes");
        return recurrentes;
    }
}
