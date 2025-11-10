package org.example.microserviciobilling.service;

import jakarta.transaction.Transactional;
import org.example.microserviciobilling.dto.TarifaDTO;
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

    public void deleteById(Long id) {
        if(!tarifaRepository.existsById(id)) {
            throw new TarifaNotFoundException("Tarifa con id: " + id +"no encontrada");
        }
        tarifaRepository.deleteById(id);
    }

    private TarifaDTO toDto(Tarifa t) {
        return new TarifaDTO(
                t.getId(),
                t.getTMinuto(),
                t.getTPausa(),
                t.getFechaExpiracion()
        );
    }

    public Tarifa toEntity(TarifaDTO dto) {
        return new Tarifa(
                dto.getId(),
                dto.getTMinuto(),
                dto.getTPausa(),
                dto.getFechaExpiracion()
        );
    }
}
