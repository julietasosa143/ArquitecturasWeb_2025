package org.example.microserviciobilling.repository;

import org.example.microserviciobilling.entities.Factura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    @Query("SELECT SUM(f.cobroTotal) FROM Factura f " +
            "WHERE YEAR(f.fechaCreacion) = :anio " +
            "AND MONTH(f.fechaCreacion) >= :mesInicio AND MONTH(f.fechaCreacion) <= :mesFin")
    public Double getReporte( @RequestParam int mesInicio, @RequestParam int mesFin,@RequestParam int anio);

    @Query("SELECT f FROM Factura f")
    Page<Factura> findUltimaFactura(Pageable pageable);



}
