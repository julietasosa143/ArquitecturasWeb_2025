package org.example.microserviciobilling.repository;

import jakarta.transaction.Transactional;
import org.example.microserviciobilling.entities.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TarifaRepository extends JpaRepository<Tarifa, Long> {
    @Query("SELECT t FROM Tarifa t "+
            "ORDER BY t.fechaExpiracion DESC")
    public Tarifa ultimaTarifa();

    @Modifying
    @Transactional
    @Query("UPDATE Tarifa t SET t.fechaExpiracion = :nuevaFecha WHERE t.id = :idTarifa")
    public void setExpiracion(@Param("idTarifa") Long idTarifa,
                              @Param("nuevaFecha") LocalDate nuevaFecha);

    @Query("SELECT t FROM Tarifa t " +
            "WHERE t.fechaExpiracion > :fecha "+
            "ORDER BY t.fechaExpiracion ASC")
    Tarifa getMasCercana(@Param("fecha") LocalDate fecha);


}