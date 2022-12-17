package com.github.mpacala00.recipeswebservice.service;

import com.github.mpacala00.recipeswebservice.model.UnitOfMeasure;

import java.util.List;
import java.util.Optional;
public interface UnitOfMeasureService {

    void save(UnitOfMeasure unitOfMeasure);

    List<UnitOfMeasure> findAll();

    Optional<UnitOfMeasure> findByName(String name);
}
