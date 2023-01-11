package com.github.mpacala00.recipeswebservice.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import com.github.mpacala00.recipeswebservice.model.Image;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
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
    public ResponseEntity<List<Recipe>> findAllRecipes() {
        List<Recipe> recipes = recipeService.findAll();
        return ResponseEntity.ok(recipes);
    }

    @PostMapping("/recipes")
    public ResponseEntity<List<Recipe>> saveRecipe(@RequestBody DtoCreateRecipe dtoCreateRecipe)
            throws URISyntaxException {
        Recipe recipe = new Recipe(dtoCreateRecipe);
        recipeService.save(recipe, false);

        return ResponseEntity.created(URI.create("/recipes/" + recipe.getId())).build();
    }

    @PostMapping(value = "/recipes/{recipeId}/image")
    public ResponseEntity<Void> attachImageToRecipe(@PathVariable("recipeId") String recipeId, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {

        Optional<Recipe> recipeOpt = recipeService.findById(recipeId);
        if (recipeOpt.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Image image = new Image(imageFile.getName(), imageFile.getContentType(),
                new Binary(BsonBinarySubType.BINARY, imageFile.getBytes()));

        Recipe recipe = recipeOpt.get();
        recipe.setImage(image);

        recipeService.save(recipe, false);
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
