package org.example.microservicioscooter.repository;

import org.example.microservicioscooter.entities.Monopatin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonopatinRepository extends MongoRepository<Monopatin,Long> {



}
