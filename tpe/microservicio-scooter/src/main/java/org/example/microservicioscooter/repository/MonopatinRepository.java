package org.example.microservicioscooter.repository;

import org.example.microservicioscooter.entities.Monopatin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonopatinRepository extends MongoRepository<Monopatin,Long> {
    @Query("{ 'x': ?0, 'y': ?1, 'estado': 'LIBRE' }")
    List<Monopatin> getMonopatinesCercanos(float x, float y);



}
