package org.example.microserviciouser.repository;

import org.example.microserviciouser.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public  interface UsuarioRepository extends JpaRepository<Usuario,Long> {


    Optional<Usuario> findByEmailIgnoreCase(String email);
}
