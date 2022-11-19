package com.github.mpacala00.recipeswebservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.github.mpacala00.recipeswebservice.model.Recipe;


public interface RecipeRepository extends MongoRepository<Recipe, String> {

    @Query("{name:'?0'}")
    Optional<Recipe> findByName(String name);

    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    List<Recipe> findAllByCategory(String category);

    public long count();

}
