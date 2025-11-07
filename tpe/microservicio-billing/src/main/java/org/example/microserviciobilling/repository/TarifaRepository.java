package org.example.microserviciobilling.repository;

import org.example.microserviciobilling.entities.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarifaRepository extends JpaRepository<Tarifa, Long> {
}
