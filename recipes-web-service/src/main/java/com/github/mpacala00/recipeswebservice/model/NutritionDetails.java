package com.github.mpacala00.recipeswebservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("nutritionDetails")
public class NutritionDetails {

    @Id
    String id;
    Double calories;
    Double fat;
    Double sugar;
    Double protein;
    Double carbohydrates;

    public NutritionDetails(Double calories, Double fat, Double sugar, Double protein, Double carbohydrates) {
        this.calories = calories;
        this.fat = fat;
        this.sugar = sugar;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
    }
}
