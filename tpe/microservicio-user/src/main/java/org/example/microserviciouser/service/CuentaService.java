package org.example.microserviciouser.service;

import org.example.microserviciouser.entities.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.example.microserviciouser.repository.CuentaRepository;

import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    RestTemplate restTemplate;

    public List<Cuenta> getAll(){
        return cuentaRepository.findAll();
    }
    public Cuenta getById(Long id){
        return cuentaRepository.findById(id).orElse(null);
    }
    public Cuenta save(Cuenta cuenta){
        return cuentaRepository.save(cuenta);
    }

}
