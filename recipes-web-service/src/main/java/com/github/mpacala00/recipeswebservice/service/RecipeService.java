package com.github.mpacala00.recipeswebservice.service;

import java.util.List;
import java.util.Optional;

import com.github.mpacala00.recipeswebservice.model.Image;
import com.github.mpacala00.recipeswebservice.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public interface RecipeService {

    void save(Recipe recipe, boolean fetchNutrition);

    void update(Recipe recipe);

    void attachImage(Recipe recipe, Image image);

    List<Recipe> findAll(Sort sort);

    Page<Recipe> findPage(Pageable paging);

    Page<Recipe> findPageSortedByDate(Pageable paging);

    List<Recipe> findByCategory(String category);

    Optional<Recipe> findByName(String name);

    Optional<Recipe> findById(String id);

    void deleteById(String id);

}
