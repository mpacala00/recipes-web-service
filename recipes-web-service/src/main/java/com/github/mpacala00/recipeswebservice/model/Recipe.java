package com.github.mpacala00.recipeswebservice.model;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.github.mpacala00.recipeswebservice.model.dto.DtoCreateRecipe;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


@Data
@AllArgsConstructor
@Builder
@Document("recipes")
public class Recipe {

    @Id
    private String id;

    private String name;
    private String category;
    private List<Ingredient> ingredients;

    public Recipe() {
        this.id = UUID.randomUUID().toString();
    }

    public Recipe(DtoCreateRecipe dtoCreateRecipe) {
        super();
        this.name = dtoCreateRecipe.getName();
        this.category = dtoCreateRecipe.getCategory();
        this.ingredients = dtoCreateRecipe.getIngredients();
    }
}
