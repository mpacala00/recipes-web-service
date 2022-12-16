package com.github.mpacala00.recipeswebservice.service;

import java.util.List;
import java.util.Optional;

import com.github.mpacala00.recipeswebservice.model.Ingredient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.github.mpacala00.recipeswebservice.model.Recipe;
import com.github.mpacala00.recipeswebservice.repository.RecipeRepository;


@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientService ingredientService;

    @Override public void save(Recipe recipe) {
        List<Ingredient> ingredients = recipe.getIngredients();
        ingredients.forEach(ingredient -> {
            Optional<Ingredient> ing = ingredientService.findByName(ingredient.getName());
            if (ing.isEmpty()) {
                ingredientService.save(Ingredient.builder().name(ingredient.getName()).build());
            }
        });

        recipeRepository.save(recipe);
    }

    @Override public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    @Override public List<Recipe> findByCategory(String category) {
        return recipeRepository.findAllByCategory(category);
    }

    @Override public Optional<Recipe> findByName(String name) {
        return recipeRepository.findByName(name);
    }

    @Override public Optional<Recipe> findById(String id) {
        return recipeRepository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        recipeRepository.deleteById(id);
    }
}
