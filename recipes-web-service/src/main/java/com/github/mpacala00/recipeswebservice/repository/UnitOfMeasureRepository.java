package com.github.mpacala00.recipeswebservice.repository;

import com.github.mpacala00.recipeswebservice.model.UnitOfMeasure;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
public interface UnitOfMeasureRepository extends MongoRepository<UnitOfMeasure, String>{

    @Query("{name:'?0'}")
    Optional<UnitOfMeasure> findByUnit(String unit);
}
