package org.example.microservicioscooter.service;

import org.example.microservicioscooter.entities.Monopatin;
import org.example.microservicioscooter.repository.MonopatinRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonopatinService {
    private final MonopatinRepository repository;

    public MonopatinService(MonopatinRepository repository) {
        this.repository = repository;
    }

    public Monopatin agregarMonopatin(Monopatin nuevo) {
        // Por defecto siempre disponible
        nuevo.setEstado("libre");
        return repository.save(nuevo);
    }

    public Monopatin cambiarEstado(Long id, String estado) {
        if (!List.of("libre", "mantenimiento", "en uso").contains(estado.toLowerCase())) {
            throw new IllegalArgumentException("Estado inválido");
        }

        Monopatin m = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Monopatin no encontrado"));

        m.setEstado(estado.toLowerCase());
        return repository.save(m);
    }

    public Optional<Monopatin> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void eliminarMonopatin(Long id) {
        Monopatin m = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Monopatin no encontrado"));
        repository.delete(m);
    }
}
