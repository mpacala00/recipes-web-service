package com.github.mpacala00.recipeswebservice.model.dto;

import com.github.mpacala00.recipeswebservice.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DtoCreateRecipe {

    private String name;
    private String category;
    private List<Ingredient> ingredients;
}
