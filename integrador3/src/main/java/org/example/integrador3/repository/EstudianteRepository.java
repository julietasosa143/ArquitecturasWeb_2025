package org.example.integrador3.repository;

import org.example.integrador3.dto.response.EstudianteResponseDTO;
import org.example.integrador3.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {

    @Query("SELECT e FROM Estudiante e order by  e.edad desc")
    public List<EstudianteResponseDTO> getEstudiantesByName();


}
