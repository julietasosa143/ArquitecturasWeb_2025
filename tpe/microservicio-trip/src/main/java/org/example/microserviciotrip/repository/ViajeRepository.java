package org.example.microserviciotrip.repository;

import org.example.microserviciotrip.entities.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje,Integer> {
    @Query("SELECT v FROM Viaje v "
            +"WHERE v.idMonopatin = :id "
            +"AND v.fechaFin > :ultimoService")
    public List<Viaje> getViajesXMonopatin(long id, LocalDate ultimoService);

    @Query("SELECT v.idMonopatin " +
            "FROM Viaje v " +
            "WHERE YEAR(v.fechaInicio) = :anio " +
            "GROUP BY v.idMonopatin " +
            "HAVING COUNT(v.id) > :minViajes")
    List<Long> getMonopatinesXViajeAnio(@Param("anio") int anio,
                                        @Param("minViajes") int minViajes);

    @Query("SELECT v.idUsuario " +
            "FROM Viaje v " +
            "WHERE YEAR(v.fechaInicio) = :anio " +
            "AND MONTH(v.fechaInicio) = :mes " +
            "GROUP BY v.idUsuario " +
            "ORDER BY COUNT(v.idUsuario) DESC")
    List<Long> getUsuariosRecurrentes( @Param("mes") int mes, @Param("anio") int anio);

    @Query("SELECT SUM(v.tiempo) AS tiempoTotal " +
                    "FROM Viaje v " +
            "WHERE v.idUsuario = :idUsuario AND " +
            "YEAR(v.fechaInicio)= :anio AND " +
            "MONTH(v.fechaInicio) = :mes " +
                    "GROUP BY v.idUsuario")
    Double getTiempoViaje(long idUsuario, int mes, int anio);

    @Query("SELECT MAX(v.id) FROM Viaje v")
    long getIdMayor();


    @Query ("SELECT v FROM Viaje v " +
            "WHERE v.id = :idViaje")
    Viaje findById(long idViaje);
}
