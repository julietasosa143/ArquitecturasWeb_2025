package org.example.microserviciouser.service;


import org.example.microserviciouser.dto.MonopatinResponseDTO;
import org.example.microserviciouser.dto.ParadaResponseDTO;
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
import java.util.Optional;

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
        List<UsuarioDTO> recurrentes= new ArrayList<>();
        List<Long> idRecurrentes= viajeFeignClient.getUsuariosRecurrentes(mes,anio);
        for(Long id:idRecurrentes){
            Usuario temporary = usuarioRepository.findById(id).orElse(null);
            if(temporary!=null){
                if(temporary.getRol().contains(tipoUsuario)){
                    recurrentes.add(new UsuarioDTO(temporary.getId(), temporary.getNombre(), temporary.getApellido(),temporary.getRol(), temporary.getPassword(), temporary.getEmail()));
                }
            }
        }
        return recurrentes;
    }

    public List<MonopatinResponseDTO> getMonopatinesCercanos(long id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        float x = usuario.getX();
        float y = usuario.getY();
        List<ParadaResponseDTO> paradas = paradaFeignClient.getParadasCercanas(x, y);
        List<MonopatinResponseDTO> monopatines= new ArrayList<>();
        for(ParadaResponseDTO parada:paradas){
            List<MonopatinResponseDTO> monopatinesCerca=
                    monopatinFeignClient.getMonopatinesCercanos
                            (parada.getX(),  parada.getY());
            for(MonopatinResponseDTO monopatin:monopatinesCerca){
                monopatines.add(monopatin);
            }

        }

        return monopatines;
    }
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmailIgnoreCase(email);
    }

}
