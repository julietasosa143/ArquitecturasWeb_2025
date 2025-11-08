package org.example.microserviciobilling.service;

import jakarta.transaction.Transactional;
import org.example.microserviciobilling.dto.FacturaDto;
import org.example.microserviciobilling.entities.Factura;
import org.example.microserviciobilling.repository.FacturaRepository;
import org.example.microserviciobilling.service.exception.FacturaNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FacturaService {
    private final FacturaRepository facturaRepository;

    public FacturaService  (FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public List<FacturaDto> findAll() {
        List<Factura> facturas = facturaRepository.findAll();
        if(facturas.isEmpty()) {
            throw new FacturaNotFoundException("No se encontraron facturas en la base de datos");
        }
        return facturas.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    public FacturaDto findById(Long id){
        Factura factura = facturaRepository.findById(id)
        .orElseThrow(()-> new FacturaNotFoundException("No se encontraron Facturas con el id: " + id));

        return toDto(factura);
    }

    public FacturaDto save(FacturaDto f) {
        Factura factura = toEntity(f);
        Factura saved = facturaRepository.save(factura);
        return toDto(saved);
    }

    public void deleteById(Long id) {
        if(!facturaRepository.existsById(id)){
            throw new FacturaNotFoundException("No se puede eliminar la factura con el id: " + id + "porque no existe.");
        }
        facturaRepository.deleteById(id);
    }
    private FacturaDto toDto(Factura f) {
        return new FacturaDto(
                f.getId(),
                f.getCobroTotal(),
                f.getFechaCreacion()
        );
    }
    private Factura toEntity(FacturaDto dto) {
        return new Factura(
                dto.getId(),
                dto.getCobroTotal(),
                dto.getFechaCreacion()
        );
    }
}
