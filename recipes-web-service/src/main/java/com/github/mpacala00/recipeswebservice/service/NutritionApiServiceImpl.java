package com.github.mpacala00.recipeswebservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mpacala00.recipeswebservice.http.NutritionDetailsReqBody;
import com.github.mpacala00.recipeswebservice.model.NutritionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NutritionApiServiceImpl implements NutritionApiService {

    String NUTRITION_API_BASE_URL = "https://api.edamam.com/api/nutrition-details";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public NutritionDetails fetchNutritionDetails() {
        String appId = "APP_ID";
        String appKey = "APP_KEY";

        String url = String.format(NUTRITION_API_BASE_URL + "?app_id=%s&app_key=%s", appId, appKey);

        NutritionDetailsReqBody body = new NutritionDetailsReqBody();
        body.setIngr(List.of(
                "500 grams flour",
                "200 ml tomato sauce")
        );

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> responseEntityStr = restTemplate.
                postForEntity(url, body, String.class, headers);
        try {
            JsonNode root = objectMapper.readTree(responseEntityStr.getBody());

            Double calories = root.path("calories").asDouble();
            JsonNode totalNutrientsNode = root.path("totalNutrients");

            Double fat = totalNutrientsNode.path("FAT").path("quantity").asDouble();
            Double sugar = totalNutrientsNode.path("SUGAR").path("quantity").asDouble();;
            Double protein = totalNutrientsNode.path("PROCNT").path("quantity").asDouble();;
            Double carbohydrates = totalNutrientsNode.path("CHOCDF").path("quantity").asDouble();

            return new NutritionDetails(calories, fat, sugar, protein, carbohydrates);
        } catch (JsonProcessingException jme) {
            throw new RuntimeException("An error occured while processing nutrition details for recipe");
        }

    }
}
