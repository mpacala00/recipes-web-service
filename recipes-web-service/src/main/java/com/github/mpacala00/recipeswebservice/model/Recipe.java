package com.github.mpacala00.recipeswebservice.model;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.github.mpacala00.recipeswebservice.model.dto.DtoCreateRecipe;


@Document("recipes")
public class Recipe {

    @Id
    private String id;

    private String name;
    private String category;
    private List<String> ingredients;

    public Recipe() {
        this.id = UUID.randomUUID().toString();
    }

    public Recipe(String id, String name, String category, List<String> ingredients) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
    }

    public Recipe(DtoCreateRecipe dtoCreateRecipe) {
        super();
        this.name = dtoCreateRecipe.getName();
        this.category = dtoCreateRecipe.getCategory();
        this.ingredients = dtoCreateRecipe.getIngredients();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
