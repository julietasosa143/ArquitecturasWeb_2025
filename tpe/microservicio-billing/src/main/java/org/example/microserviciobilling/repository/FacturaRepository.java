package org.example.microserviciobilling.repository;

import org.example.microserviciobilling.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    @Query("SELECT SUM(f.cobroTotal) FROM Factura f " +
            "WHERE YEAR(f.fechaCreacion) = :anio " +
            "AND MONTH(f.fechaCreacion) > :inicio AND MONTH(f.fechaCreacion) < :fin")
    public Double getReporte(@RequestParam int anio, @RequestParam int inicio, @RequestParam int fin);


}
