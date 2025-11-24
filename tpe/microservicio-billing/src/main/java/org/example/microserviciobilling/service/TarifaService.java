package org.example.microserviciobilling.service;

import jakarta.transaction.Transactional;
import org.example.microserviciobilling.dto.TarifaDTO;
import org.example.microserviciobilling.entities.Tarifa;
import org.example.microserviciobilling.repository.TarifaRepository;
import org.example.microserviciobilling.service.exception.TarifaNotFoundException;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TarifaService {

    private final TarifaRepository tarifaRepository;

    public TarifaService(TarifaRepository tarifaRepository) {
        this.tarifaRepository = tarifaRepository;
    }
    public List<TarifaDTO> findAll() {
        List<Tarifa> tarifas= tarifaRepository.findAll();
        if(tarifas.isEmpty()){
            throw new TarifaNotFoundException("No se encontraron Tarifas");
        }
        return tarifas.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public TarifaDTO findById(Long id) {
        Tarifa tarifa = tarifaRepository.findById(id)
            .orElseThrow(()-> new TarifaNotFoundException("No se encontro tarifa con el id :" + id));

        return toDto(tarifa);
    }

    public TarifaDTO save(TarifaDTO t) {
        Tarifa tarifa = toEntity(t);
        Tarifa saved = tarifaRepository.save(tarifa);
        return toDto(saved);
    }
    public TarifaDTO ajustar(TarifaDTO dto){
        Long nuevoId = obtenerSiguienteId();
        dto.setId(nuevoId);
        if(dto.getFechaCreacion().isAfter(this.ultimaTarifa().getFechaExpiracion())){
            dto.setFechaCreacion(ultimaTarifa().getFechaExpiracion());
        }else if(!dto.getFechaCreacion().isEqual(this.ultimaTarifa().getFechaExpiracion())){
            Tarifa tarifa = getMasCercana(dto.getFechaCreacion());
            tarifaRepository.setExpiracion(this.ultimaTarifa().getId(),dto.getFechaCreacion());

        }
        Tarifa saved = toEntity(dto);
        Tarifa t = tarifaRepository.save(saved);
        return toDto(t);
    }
    public Tarifa getMasCercana(LocalDate fecha){
        return tarifaRepository.getMasCercana(fecha).get(0);
    }
    public Tarifa ultimaTarifa(){
        Tarifa t = tarifaRepository.ultimaTarifa().get(0);
        return t;
    }
    public void deleteById(Long id) {
        if(!tarifaRepository.existsById(id)) {
            throw new TarifaNotFoundException("Tarifa con id: " + id +"no encontrada");
        }
        tarifaRepository.deleteById(id);
    }

    public Long obtenerSiguienteId() {
        Long maxId = tarifaRepository.findMaxId();
        if (maxId == null) {
            return 1L; // Si no hay nada en la tabla, empezamos desde 1
        }
        return maxId + 1;
    }
    private TarifaDTO toDto(Tarifa t) {
        return new TarifaDTO(
                t.getId(),
                t.getFechaCreacion(),
                t.getFechaExpiracion(),
                t.getPrecio(),
                t.getPrecioEspecial()
        );
    }

    public Tarifa toEntity(TarifaDTO dto) {
        return new Tarifa(
                dto.getId(),
                dto.getFechaCreacion(),
                dto.getFechaExpiracion(),
                dto.getPrecio(),
                dto.getPrecioEspecial()
        );
    }
}
