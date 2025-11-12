package org.example.microserviciotrip.services;


import org.example.microserviciotrip.dto.ViajeDTO;
import org.example.microserviciotrip.entities.Viaje;
import jakarta.transaction.Transactional;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.example.microserviciotrip.repository.ViajeRepository;
import org.example.microserviciotrip.services.exception.ViajeNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ViajeService {


    private final ViajeRepository viajeRepository;
    private final ResourcePatternResolver resourcePatternResolver;

    public ViajeService(ViajeRepository viajeRepository, ResourcePatternResolver resourcePatternResolver) {
        this.viajeRepository = viajeRepository;
        this.resourcePatternResolver = resourcePatternResolver;
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

    public ViajeDTO findById(int id) {
        Viaje  viaje = viajeRepository.findById(id)
                .orElseThrow(()-> new ViajeNotFoundException("no se encontro viaje con el id:" + id));

        return toDTO(viaje);
    }
    public ViajeDTO save(ViajeDTO v){
        Viaje viaje = toEntity(v);
        Viaje saved = viajeRepository.save(viaje);
        return toDTO(saved);

    }

    public void deleteById(Integer id) {
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
                v.getKilometros(),
                v.getTiempo(),
                v.getTarifa(),
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
                dto.getKilometros(),
                dto.getTiempo(),
                dto.getTarifa(),
                dto.getIdMonopatin(),
                dto.getIdUsuario(),
                dto.getFechaInicio(),
                dto.getFechaFin()

        );
    }


    public List<ViajeDTO> getViajesXMonopatin(long id, LocalDate ultimoService) {
        List<Viaje> viajes = viajeRepository.getViajesXMonopatin(id, ultimoService);
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
}
