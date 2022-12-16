package com.github.mpacala00.recipeswebservice.service;

import com.github.mpacala00.recipeswebservice.model.Ingredient;
import com.github.mpacala00.recipeswebservice.model.Recipe;

import java.util.List;

public interface IngredientService {

    void save(Ingredient ingredient);

    List<Ingredient> findAll();
}
