package com.github.mpacala00.recipeswebservice.service;

import com.github.mpacala00.recipeswebservice.model.UnitOfMeasure;
import com.github.mpacala00.recipeswebservice.repository.UnitOfMeasureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Override
    public void save(UnitOfMeasure unitOfMeasure) {
        unitOfMeasureRepository.save(unitOfMeasure);
    }

    @Override
    public List<UnitOfMeasure> findAll() {
        return unitOfMeasureRepository.findAll();
    }

    @Override
    public Optional<UnitOfMeasure> findByUnit(String unit) {
        return unitOfMeasureRepository.findByUnit(unit);
    }
}
