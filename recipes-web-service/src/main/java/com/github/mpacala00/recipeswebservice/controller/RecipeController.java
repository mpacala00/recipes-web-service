package com.github.mpacala00.recipeswebservice.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.mpacala00.recipeswebservice.model.Recipe;
import com.github.mpacala00.recipeswebservice.model.dto.DtoCreateRecipe;
import com.github.mpacala00.recipeswebservice.service.RecipeService;


@RestController
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    public ResponseEntity<List<Recipe>> findAllRecipes() {
        List<Recipe> recipes = recipeService.findAll();
        return ResponseEntity.ok(recipes);
    }

    @PostMapping("/recipes")
    public ResponseEntity<List<Recipe>> saveRecipe(@RequestBody DtoCreateRecipe dtoCreateRecipe)
            throws URISyntaxException {
        Recipe recipe = new Recipe(dtoCreateRecipe);
        recipeService.save(recipe);

        return ResponseEntity.created(URI.create("/recipes/" + recipe.getId())).build();
    }

    @GetMapping("/recipes/{id}")
    public ResponseEntity<Recipe> findRecipeById(@PathVariable String id) {
        Optional<Recipe> recipeOpt = recipeService.findById(id);

        if (recipeOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(recipeOpt.get());
    }

    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<Recipe> deleteRecipeById(@PathVariable String id) {
        recipeService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
