package com.github.mpacala00.recipeswebservice.service;

import com.github.mpacala00.recipeswebservice.model.Ingredient;
import com.github.mpacala00.recipeswebservice.model.NutritionDetails;

import java.util.List;

public interface NutritionApiService {

    NutritionDetails fetchNutritionDetails(List<Ingredient> ingredientList);
}
