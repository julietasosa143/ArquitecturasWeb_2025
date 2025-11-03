package org.example.microserviciotrip.repository;

import org.example.microserviciotrip.entities.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje,Integer> {

}
