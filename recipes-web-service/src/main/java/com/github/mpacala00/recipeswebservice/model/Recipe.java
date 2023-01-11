package com.github.mpacala00.recipeswebservice.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.github.mpacala00.recipeswebservice.model.dto.DtoCreateRecipe;


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
    private String description;
    private String instructions;
    private NutritionDetails nutritionDetails;
    private Image image;
    @CreatedDate
    private LocalDateTime createTime;

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
