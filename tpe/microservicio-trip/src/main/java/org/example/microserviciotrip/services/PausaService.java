package org.example.microserviciotrip.services;

import org.example.microserviciotrip.dto.PausaDTO;
import org.example.microserviciotrip.entities.Pausa;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.example.microserviciotrip.repository.PausaRepository;
import org.example.microserviciotrip.services.exception.PausaNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PausaService {
    private final PausaRepository pausaRepository;

    public PausaService(PausaRepository pausaRepository) {
        this.pausaRepository = pausaRepository;
    }

    private PausaDTO toDTO(Pausa p) {
        return new PausaDTO(p.getId(), p.getFechaInicio(), p.getFechaFin(), p.getTotal());
    }

    private Pausa toEntity(PausaDTO dto) {
        return new Pausa(dto.getId(), dto.getFechaInicio(), dto.getFechaFin(), dto.getTotal());
    }

    public List<PausaDTO> findAll() {
        return pausaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PausaDTO findById(Integer id) {
        Pausa pausa = pausaRepository.findById(id)
                .orElseThrow(() -> new PausaNotFoundException("No se encontró la pausa con id: " + id));
        return toDTO(pausa);
    }

    public PausaDTO save(PausaDTO dto) {
        Pausa pausa = toEntity(dto);
        Pausa saved = pausaRepository.save(pausa);
        return toDTO(saved);
    }

    public void deleteById(Integer id) {
        if (!pausaRepository.existsById(id)) {
            throw new PausaNotFoundException("No se puede eliminar: pausa con id " + id + " no existe");
        }
        pausaRepository.deleteById(id);
    }
}
