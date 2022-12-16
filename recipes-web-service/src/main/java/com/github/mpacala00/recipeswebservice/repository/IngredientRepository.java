package com.github.mpacala00.recipeswebservice.repository;

import com.github.mpacala00.recipeswebservice.model.Ingredient;
import com.github.mpacala00.recipeswebservice.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface IngredientRepository extends MongoRepository<Ingredient, String> {

    @Query("{name:'?0'}")
    Optional<Ingredient> findByName(String name);
}
