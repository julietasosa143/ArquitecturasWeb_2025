package org.example.microserviciotrip.services;


import org.example.microserviciotrip.dto.ParadaDTO;
import org.example.microserviciotrip.dto.ViajeDTO;
import org.example.microserviciotrip.dto.ViajeDTOfin;
import org.example.microserviciotrip.dto.ViajeDTOinicio;
import org.example.microserviciotrip.entities.Viaje;
import jakarta.transaction.Transactional;
import org.example.microserviciotrip.feignClient.FacturaFeignClient;
import org.example.microserviciotrip.feignClient.ParadaFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.microserviciotrip.repository.ViajeRepository;
import org.example.microserviciotrip.services.exception.ViajeNotFoundException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ViajeService {
    @Autowired
    private final ViajeRepository viajeRepository;
    @Autowired
    private FacturaFeignClient facturaFeignClient;
    @Autowired
    private ParadaFeignClient paradaFeignClient;

    public ViajeService(ViajeRepository viajeRepository) {
        this.viajeRepository = viajeRepository;
    }


    public List<ViajeDTO> findAll() {
        List<Viaje> viajes= viajeRepository.findAll();
        if(viajes.isEmpty()){
            throw new ViajeNotFoundException("no se encontro viajes en la base de datos");
        }
        return viajes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ViajeDTO findById(long id) {
        Viaje  viaje = viajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));
        return toDTO(viaje);
    }
    public ViajeDTO save(ViajeDTO v){
        Viaje viaje = toEntity(v);
        Viaje saved = viajeRepository.save(viaje);
        return toDTO(saved);

    }

    public void deleteById(Long id) {
        if(!viajeRepository.existsById(id)){
            throw new ViajeNotFoundException("no se puede elimianr el viaje con el id:" + id + " por que no existe");

        }
        viajeRepository.deleteById(id);
    }
    private ViajeDTO toDTO(Viaje v) {
        return new ViajeDTO(
                v.getId(),
                v.getIdParadaInicio(),
                v.getIdParadaFin(),
                v.getTiempo(),
                v.getKilometros(),
                v.getPrecio(),
                v.getIdMonopatin(),
                v.getIdUsuario(),
                v.getFechaInicio(),
                v.getFechaFin()
        );
    }

    private Viaje toEntity(ViajeDTO dto) {
        return new Viaje(
                dto.getId(),
                dto.getIdParadaInicio(),
                dto.getIdParadaFin(),
                dto.getTiempo(),
                dto.getKilometros(),
                dto.getPrecio(),
                dto.getIdMonopatin(),
                dto.getIdUsuario(),
                dto.getFechaInicio(),
                dto.getFechaFin()

        );
    }


    public List<ViajeDTO> getViajesXMonopatin(long id, LocalDate ultimoService) {

        LocalDateTime ultimoServiceDT = ultimoService.atStartOfDay();

        List<Viaje> viajes = viajeRepository.getViajesXMonopatin(id, ultimoServiceDT);

        return viajes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Long> getMonopatinesXViajeXAnio(int anio, int minViajes){
        List<Long> ids = viajeRepository.getMonopatinesXViajeAnio(anio,minViajes);
        return ids;
    }

    public List<Long> getUsuariosRecurrentes(int mes, int anio){
        System.out.println("Buscando usuarios recurrentes del mes " + mes + " del año " + anio);
        List<Long> recurrentes= viajeRepository.getUsuariosRecurrentes(mes,anio);
        System.out.println("Resultado: " + recurrentes);
        return recurrentes;
    }

    public Double getTiempoViaje(long idUsuario, int mes, int anio){
        return viajeRepository.getTiempoViaje(idUsuario, mes, anio);
    }

    public ViajeDTO inicializarViaje(ViajeDTOinicio dto){
        Viaje viaje = new Viaje();
        viaje.setId((viajeRepository.getIdMayor())+1);
        viaje.setIdParadaInicio(dto.getIdParadaInicio());
        viaje.setIdParadaFin(dto.getIdParadaFin());
        viaje.setIdMonopatin(dto.getIdMonopatin());
        viaje.setFechaInicio(LocalDateTime.now());
        viajeRepository.save(viaje);
        return toDTO(viaje);

    }

    public ViajeDTO finalizarViaje(ViajeDTOfin dto){
        Viaje viaje = viajeRepository.findById(dto.getIdViaje())
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));
        viaje.setFechaFin(dto.getFechaFin());
        System.out.println("FechaInicio = " + viaje.getFechaInicio());
        System.out.println("FechaFin = " + dto.getFechaFin());
        viaje.setTiempo((Duration.between(viaje.getFechaInicio(), dto.getFechaFin())).toMinutes());
        viaje.setPrecio(facturaFeignClient.getPrecioViaje(viaje.getId(),viaje.getTiempo(), viaje.getTiempoPausas(), viaje.getFechaFin()));
        ParadaDTO paradaInicio = paradaFeignClient.getById(viaje.getIdParadaInicio());
        ParadaDTO paradaFin = paradaFeignClient.getById(viaje.getIdParadaFin());
        double dx = paradaFin.getX() - paradaInicio.getX();
        double dy = paradaFin.getY() - paradaFin.getY();
        viaje.setKilometros(Math.sqrt(dx * dx + dy * dy));
        viajeRepository.save(viaje);
        ViajeDTO response = new ViajeDTO(viaje.getId(),
                viaje.getIdParadaInicio(), viaje.getIdParadaFin(),
                viaje.getTiempo(),viaje.getKilometros(),viaje.getPrecio(), viaje.getIdMonopatin(),

                viaje.getIdUsuario(), viaje.getFechaInicio(), viaje.getFechaFin());
        return response;
    }



}

