package org.example.microserviciobilling.repository;

import org.example.microserviciobilling.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
