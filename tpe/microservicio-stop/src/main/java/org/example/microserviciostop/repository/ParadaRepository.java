package org.example.microserviciostop.repository;

import org.example.microserviciostop.DTO.ParadaResponseDTO;
import org.example.microserviciostop.entity.Parada;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long> {

    @Query("SELECT p FROM Parada p " +
            "ORDER BY ((p.x - :xUsuario) * (p.x - :xUsuario))\n" +
            "          + ((p.y - :yUsuario) * (p.y - :yUsuario))")
    List<Parada> getParadasCercanas(@Param("xUsuario") float x, @Param("yUsuario") float y, Pageable pageable);

}