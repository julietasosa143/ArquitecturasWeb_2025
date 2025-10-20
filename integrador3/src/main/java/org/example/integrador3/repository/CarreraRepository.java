package org.example.integrador3.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.example.integrador3.dto.response.CarreraInscriptosResponseDTO;
import org.example.integrador3.model.Carrera;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera,Integer> {
    //Recupera las carreras con estudiantes inscriptos, ordenadas por cantidad de inscriptos
    @Query("SELECT new org.example.integrador3.dto.response.CarreraInscriptosResponseDTO(c.nombreCarrera, COUNT(e)) " +
            "FROM Carrera c " +
            "JOIN c.inscripciones e " +
            "GROUP BY c.nombreCarrera " +
            "ORDER BY COUNT(e) DESC")
    List<CarreraInscriptosResponseDTO> findCarreraConInscriptosOrdenadas();
}
