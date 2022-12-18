package com.github.mpacala00.recipeswebservice.http;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NutritionDetailsReqBody {

    private List<String> ingr;
}
