package org.example.microserviciotrip.repository;

import org.example.microserviciotrip.entities.Pausa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PausaRepository  extends JpaRepository<Pausa,Integer> {
}
