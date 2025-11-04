package org.example.microserviciostop.repository;

import org.example.microserviciostop.entity.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long> {

    List<Parada> findByUserId(Long id);
}