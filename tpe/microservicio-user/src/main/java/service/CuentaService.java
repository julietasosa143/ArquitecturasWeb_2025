package service;

import entities.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import repository.CuentaRepository;

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
