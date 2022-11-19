package com.github.mpacala00.recipeswebservice.service;

import java.util.List;
import java.util.Optional;

import com.github.mpacala00.recipeswebservice.model.Recipe;


public interface RecipeService {

    void save(Recipe recipe);

    List<Recipe> findAll();

    List<Recipe> findByCategory(String category);

    Optional<Recipe> findByName(String name);

    Optional<Recipe> findById(String id);

}
