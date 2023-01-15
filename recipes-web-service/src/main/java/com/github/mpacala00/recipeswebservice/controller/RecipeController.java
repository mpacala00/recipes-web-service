package com.github.mpacala00.recipeswebservice.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import com.github.mpacala00.recipeswebservice.model.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.mpacala00.recipeswebservice.model.Recipe;
import com.github.mpacala00.recipeswebservice.model.dto.DtoCreateRecipe;
import com.github.mpacala00.recipeswebservice.service.RecipeService;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    public ResponseEntity<List<Recipe>> findAllRecipes(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        List<Recipe> recipes = recipeService.findAll(null);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/recipes/latest")
    public ResponseEntity<Page<Recipe>> findAllLatestRecipes(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "4") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Recipe> recipePage = recipeService.findPageSortedByDate(paging);
        return ResponseEntity.ok(recipePage);
    }

    @PostMapping("/recipes")
    public ResponseEntity<List<Recipe>> saveRecipe(@RequestBody DtoCreateRecipe dtoCreateRecipe,
            @RequestParam(value = "fetchNutrition", required = false, defaultValue = "false") boolean fetchNutrition)
            throws URISyntaxException {
        Recipe recipe = new Recipe(dtoCreateRecipe);
        recipeService.save(recipe, fetchNutrition);

        return ResponseEntity.created(URI.create("/recipes/" + recipe.getId())).build();
    }

    @PostMapping(value = "/recipes/{recipeId}/image")
    public ResponseEntity<Void> attachImageToRecipe(@PathVariable("recipeId") String recipeId, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {

        Optional<Recipe> recipeOpt = recipeService.findById(recipeId);
        if (recipeOpt.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        recipeService.attachImage(recipeOpt.get(), Image.createImage(imageFile));
        return ResponseEntity.ok().build();
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
