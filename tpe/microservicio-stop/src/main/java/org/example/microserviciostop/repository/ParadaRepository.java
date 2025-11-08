package org.example.microserviciostop.repository;

import org.example.microserviciostop.entity.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long> {

    List<Parada> findByUserId(Long id);

    @Query("SELECT p FROM Parada p ORDER BY SQRT(POWER(p.x - :x, 2) + POWER(p.y - :y, 2)) ASC")
    Optional<Parada> findParadaMasCercana(@Param("x") double x, @Param("y") double y);
}