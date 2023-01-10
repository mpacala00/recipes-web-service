package com.github.mpacala00.recipeswebservice.service;

import java.util.List;
import java.util.Optional;

import com.github.mpacala00.recipeswebservice.model.Ingredient;
import com.github.mpacala00.recipeswebservice.model.NutritionDetails;
import com.github.mpacala00.recipeswebservice.model.UnitOfMeasure;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.github.mpacala00.recipeswebservice.model.Recipe;
import com.github.mpacala00.recipeswebservice.repository.RecipeRepository;


@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;
    private final NutritionDetailsService nutritionDetailsService;

    @Override public void save(Recipe recipe, boolean fetchNutrition) {
        List<Ingredient> ingredients = recipe.getIngredients();
        ingredients.forEach(ingredient -> {
            Optional<Ingredient> ing = ingredientService.findByName(ingredient.getName());
            if (ing.isEmpty()) {
                ingredientService.save(Ingredient.builder().name(ingredient.getName()).build());
            }

            if (ingredient.getUnitOfMeasure() != null){
                Optional<UnitOfMeasure> uom =  unitOfMeasureService.findByUnit(ingredient.getUnitOfMeasure().getUnit());
                if (uom.isEmpty()){
                    unitOfMeasureService.save(UnitOfMeasure.builder().unit(ingredient.getUnitOfMeasure().getUnit()).build());
                }
            }
        });

        if (fetchNutrition) {
            NutritionDetails nutritionDetails = nutritionDetailsService.getDetailsFromApi(ingredients);
            recipe.setNutritionDetails(nutritionDetails);
        }

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
