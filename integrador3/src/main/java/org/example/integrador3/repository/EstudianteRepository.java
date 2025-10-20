package org.example.integrador3.repository;

import org.example.integrador3.dto.response.EstudiantesPorCarreraCiudadResponseDTO;
import org.example.integrador3.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante,Integer> {

    @Query("SELECT e FROM Estudiante e ORDER BY e.edadEstudiante DESC")
    public List<Estudiante> getAllByAge();

    @Query("SELECT e FROM Estudiante e WHERE "
            +"(:nombre is null or e.nombreEstudiante LIKE %:nombre%) AND"
            +"(:apellido is null or e.apellidoEstudiante LIKE %:apellido%) AND"
            +"(:genero is null or e.generoEstudiante = :genero) AND"
            +"(:edad is null or e.edadEstudiante  =:edad) AND"
            +"(:dni is null or e.dniEstudiante  =:dni) AND"
            +"(:city is null or e.ciudadResidencia LIKE %:city%) AND"
            +"(:libretaUniversitaria is null or e.libretaUniversitaria = :libretaUniversitaria)"
        )
    public List<Estudiante> search(String nombre, String apellido, String genero, Integer edad,Integer dni,String city, Integer libretaUniversitaria);

    @Query("SELECT new org.example.integrador3.dto.response.EstudiantesPorCarreraCiudadResponseDTO(" +
            "e.nombreEstudiante, e.apellidoEstudiante, c.nombreCarrera, e.ciudadResidencia) " +
            "FROM Inscripcion i " +
            "JOIN i.estudiante e " +
            "JOIN i.carrera c " +
            "WHERE c.nombreCarrera = :nombreCarrera " +
            "AND e.ciudadResidencia = :ciudadResidencia"
    )
    List<EstudiantesPorCarreraCiudadResponseDTO> getPorCarrerayCiudad(
            @Param("nombreCarrera") String nombreCarrera,
            @Param("ciudadResidencia") String ciudadResidencia);
}
