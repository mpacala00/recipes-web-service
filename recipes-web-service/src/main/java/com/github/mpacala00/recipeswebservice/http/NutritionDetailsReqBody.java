package com.github.mpacala00.recipeswebservice.http;

import lombok.Data;

import java.util.List;

@Data
public class NutritionDetailsReqBody {

    private List<String> ingr;
}
