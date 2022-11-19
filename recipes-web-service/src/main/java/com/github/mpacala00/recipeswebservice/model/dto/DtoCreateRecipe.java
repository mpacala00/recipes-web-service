package com.github.mpacala00.recipeswebservice.model.dto;

import java.util.List;


public class DtoCreateRecipe {

    private String name;
    private String category;
    private List<String> ingredients;

    public DtoCreateRecipe() {
    }

    public DtoCreateRecipe(String name, String category, List<String> ingredients) {
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
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
