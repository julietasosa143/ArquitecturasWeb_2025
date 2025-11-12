package org.example.microserviciobilling.repository;

import org.example.microserviciobilling.entities.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TarifaRepository extends JpaRepository<Tarifa, Long> {
@Query(
        "SELECT t " + "FROM Tarifa t " +
                "WHERE t.fechaExpiracion IS NULL"
)
public Tarifa ultimaTarifa();
}
