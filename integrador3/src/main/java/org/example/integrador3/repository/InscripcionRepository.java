package org.example.integrador3.repository;


import org.example.integrador3.model.Inscripcion;
import org.example.integrador3.model.InscripcionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository<Inscripcion, InscripcionId> {
}
