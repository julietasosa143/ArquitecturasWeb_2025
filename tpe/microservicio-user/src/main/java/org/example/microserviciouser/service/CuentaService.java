package org.example.microserviciouser.service;

import jakarta.transaction.Transactional;
import org.example.microserviciouser.entities.Cuenta;
import org.example.microserviciouser.service.exception.CuentaNotFoundException;
import org.example.microserviciouser.service.exception.CuentaYaAnuladaException;
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
    @Transactional
    public void anularCuenta(Long id){
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(()-> new CuentaNotFoundException("la cuenta  con id: "+ id+ "no existe"));
        if(!cuenta.isActiva()){
            throw new CuentaYaAnuladaException("La cuenta ya está inhabilitada");
        }
        cuenta.setActiva(false);

    }
}
