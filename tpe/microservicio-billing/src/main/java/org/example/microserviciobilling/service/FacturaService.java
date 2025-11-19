package org.example.microserviciobilling.service;

import jakarta.transaction.Transactional;
import org.example.microserviciobilling.dto.FacturaDTO;
import org.example.microserviciobilling.entities.Factura;
import org.example.microserviciobilling.repository.FacturaRepository;
import org.example.microserviciobilling.repository.TarifaRepository;
import org.example.microserviciobilling.service.exception.FacturaNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FacturaService {
    private final FacturaRepository facturaRepository;
    private final TarifaRepository  tarifaRepository;

    public FacturaService  (FacturaRepository facturaRepository,  TarifaRepository tarifaRepository) {
        this.facturaRepository = facturaRepository;
        this.tarifaRepository = tarifaRepository;
    }

    public List<FacturaDTO> findAll() {
        List<Factura> facturas = facturaRepository.findAll();
        if(facturas.isEmpty()) {
            throw new FacturaNotFoundException("No se encontraron facturas en la base de datos");
        }
        return facturas.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    public FacturaDTO findById(Long id){
        Factura factura = facturaRepository.findById(id)
        .orElseThrow(()-> new FacturaNotFoundException("No se encontraron Facturas con el id: " + id));

        return toDto(factura);
    }

    public FacturaDTO save(FacturaDTO f) {
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
    private FacturaDTO toDto(Factura f) {
        return new FacturaDTO(
                f.getId(),
                f.getCobroTotal(),
                f.getIdViaje(),
                f.getFechaCreacion()
        );
    }
    private Factura toEntity(FacturaDTO dto) {
        return new Factura(
                dto.getId(),
                dto.getCobroTotal(),
                dto.getIdViaje(),
                dto.getFechaCreacion()
        );
    }
    public Double getReporte(int mesInicio, int mesFin, int anio){
        Double total = facturaRepository.getReporte(mesInicio, mesFin, anio);
        return total;
    }

    public Double calcularPrecio(double tiempoTotal, double tiempoPausas, LocalDate fechaFin){
        if(tiempoPausas <=15){
            return tiempoTotal * tarifaRepository.getTarifaNormal(fechaFin);
        }
        else if(tiempoPausas>15){
            return tiempoTotal*tarifaRepository.getTarifaEspecial(fechaFin);
        }else{
            return 0.0;
        }
    }

    public Factura crear(long idViaje, Double precio){
        Factura factura = new Factura();
        factura.setIdViaje(idViaje);
        factura.setCobroTotal(precio);
        Pageable pageable = PageRequest.of(0, 1, Sort.by("id").descending());
        Factura ultima = facturaRepository.findUltimaFactura(pageable)
                .getContent()
                .stream()
                .findFirst()
                .orElse(null);
        factura.setId(ultima.getId()+1);
        factura.setFechaCreacion(LocalDate.now());
        facturaRepository.save(factura);
        return factura;
    }

}
