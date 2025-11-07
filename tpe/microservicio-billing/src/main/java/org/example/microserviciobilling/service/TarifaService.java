package org.example.microserviciobilling.service;

import jakarta.transaction.Transactional;
import org.example.microserviciobilling.dto.TarifaDto;
import org.example.microserviciobilling.entities.Tarifa;
import org.example.microserviciobilling.repository.TarifaRepository;
import org.example.microserviciobilling.service.exception.TarifaNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TarifaService {

    private final TarifaRepository tarifaRepository;

    public TarifaService(TarifaRepository tarifaRepository) {
        this.tarifaRepository = tarifaRepository;
    }
    public List<TarifaDto> findAll() {
        List<Tarifa> tarifas= tarifaRepository.findAll();
        if(tarifas.isEmpty()){
            throw new TarifaNotFoundException("No se encontraron Tarifas");
        }
        return tarifas.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public TarifaDto findById(Long id) {
        Tarifa tarifa = tarifaRepository.findById(id)
            .orElseThrow(()-> new TarifaNotFoundException("No se encontro tarifa con el id :" + id));

        return toDto(tarifa);
    }
    public TarifaDto save(TarifaDto t) {
        Tarifa tarifa = toEntity(t);
        Tarifa saved = tarifaRepository.save(tarifa);
        return toDto(saved);
    }

    public void deleteById(Long id) {
        if(!tarifaRepository.existsById(id)) {
            throw new TarifaNotFoundException("Tarifa con id: " + id +"no encontrada");
        }
        tarifaRepository.deleteById(id);
    }

    private TarifaDto toDto(Tarifa t) {
        return new TarifaDto(
                t.getId(),
                t.getTMinuto(),
                t.getTPausa(),
                t.getFechaExpiracion()
        );
    }

    public Tarifa toEntity(TarifaDto dto) {
        return new Tarifa(
                dto.getId(),
                dto.getTMinuto(),
                dto.getTPausa(),
                dto.getFechaExpiracion()
        );
    }
}
