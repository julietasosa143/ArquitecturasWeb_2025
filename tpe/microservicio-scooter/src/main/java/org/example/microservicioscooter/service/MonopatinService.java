package org.example.microservicioscooter.service;

import org.example.microservicioscooter.dto.ReporteMantenimientoDTOResponse;
import org.example.microservicioscooter.entities.Monopatin;
import org.example.microservicioscooter.fiegnClient.ViajeFeignClient;
import org.example.microservicioscooter.repository.MonopatinRepository;
import org.example.microserviciotrip.entities.Pausa;
import org.example.microserviciotrip.entities.Viaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MonopatinService {
    @Autowired
    private MonopatinRepository repository;

    private ViajeFeignClient viajeFeignClient;


    public MonopatinService(MonopatinRepository repository, ViajeFeignClient viajeFeignClient)
    {
        this.repository = repository;
        this.viajeFeignClient = viajeFeignClient;
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

    public ReporteMantenimientoDTOResponse getReporteMantenimientoSinPausa(long id){
        List<Viaje> viajes = viajeFeignClient.getViajesXMonopatin(id, repository.findById(id).get().getUltimoService());
        double kmRecorridos =0;
        double tiempoTotal = 0;
        for(Viaje v: viajes){
            kmRecorridos+= v.getKilometros();
            tiempoTotal+= v.getTiempo();
        }
        ReporteMantenimientoDTOResponse reporte= new ReporteMantenimientoDTOResponse(kmRecorridos,tiempoTotal);
        return reporte;

    }
    public ReporteMantenimientoDTOResponse getReporteMantenimientoConPausa(long id){
        List<Viaje> viajes = viajeFeignClient.getViajesXMonopatin(id, repository.findById(id).get().getUltimoService());
        double kmRecorridos =0;
        double tiempoTotal = 0;
        double tiempoPausas=0;
        double tiempoFinal=0;
        for(Viaje v: viajes){
            kmRecorridos+= v.getKilometros();
            tiempoTotal+= v.getTiempo();
            List<Pausa> pausas= v.getPausas();
            for(Pausa p: pausas){
                tiempoPausas+= p.getTotal();
            }
            tiempoFinal = tiempoTotal-tiempoPausas;
        }
        ReporteMantenimientoDTOResponse reporte = new ReporteMantenimientoDTOResponse(kmRecorridos,tiempoFinal);
        return reporte;


    }
}
