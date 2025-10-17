package org.example.integrador3.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.example.integrador3.dto.response.CarreraInscriptosResponseDTO;
import org.example.integrador3.model.Carrera;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long>{
    //Recupera las carreras con estudiantes inscriptos, ordenadas por cantidad de inscriptos
    @Query("SELECT new org.example.integrador3.dto.response.CarreraInscriptosResponseDTO(c.nombre, COUNT(e)) " +
    "FROM Carrera c " +
    "JOIN c.estudiantes e " +
    "GROUP BY c.nombre " +
    "ORDER BY COUNT(e) DESC")
    List<CarreraInscriptosResponseDTO> findCarreraConInscriptosOrdenadas();
}
