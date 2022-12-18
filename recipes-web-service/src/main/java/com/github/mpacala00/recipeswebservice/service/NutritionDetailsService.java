package com.github.mpacala00.recipeswebservice.service;

import com.github.mpacala00.recipeswebservice.model.Ingredient;
import com.github.mpacala00.recipeswebservice.model.NutritionDetails;

import java.util.List;
import java.util.Optional;

public interface NutritionDetailsService {

    Optional<NutritionDetails> findById(String id);

    void save(NutritionDetails nutritionDetails);

    NutritionDetails getDetailsFromApi(List<Ingredient> ingredientList);
}
