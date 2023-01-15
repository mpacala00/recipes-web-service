package com.github.mpacala00.recipeswebservice.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.github.mpacala00.recipeswebservice.model.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.github.mpacala00.recipeswebservice.repository.RecipeRepository;


@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;
    private final NutritionDetailsService nutritionDetailsService;

    @Override public void save(Recipe recipe, boolean fetchNutrition) {
        validateRecipe(recipe);

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

    @Override
    public void update(Recipe recipe) {
        validateRecipeExists(recipe);

        recipeRepository.save(recipe);
    }

    @Override
    public void attachImage(Recipe recipe, Image image) {
        validateRecipeExists(recipe);
        recipe.setImage(image);

        recipeRepository.save(recipe);
    }

    @Override public List<Recipe> findAll(Sort sort) {
        if (sort != null) {
            return recipeRepository.findAll(sort);
        }
        return recipeRepository.findAll();
    }

    @Override
    public Page<Recipe> findPage(Pageable paging) {
        return recipeRepository.findAll(paging);
    }

    @Override
    public Page<Recipe> findPageSortedByDate(Pageable paging) {
        Page<Recipe> page = findPage(paging);
        return sortByCreatedDate(page);
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

    private void validateRecipe(Recipe recipe) {
        if (findByName(recipe.getName()).isPresent()) {
            throw new RuntimeException(String.format("Recipe of name %s is present", recipe.getName()));
        }
    }

    private void validateRecipeExists(Recipe recipe) {
        if (recipe.getId() == null || recipeRepository.findById(recipe.getId()).isEmpty()) {
            throw new RuntimeException(String.format("Recipe of id=%s not found", recipe.getId()));
        }
    }

    private Page<Recipe> sortByCreatedDate(Page<Recipe> page) {
        List<Recipe> sortedRecipes = page.stream().sorted(Comparator.comparing(Recipe::getCreateTime)).collect(Collectors.toList());
        return new PageImpl<>(sortedRecipes);
    }

}
