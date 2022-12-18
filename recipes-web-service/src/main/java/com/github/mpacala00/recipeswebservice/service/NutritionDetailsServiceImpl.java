package com.github.mpacala00.recipeswebservice.service;

import com.github.mpacala00.recipeswebservice.model.Ingredient;
import com.github.mpacala00.recipeswebservice.model.NutritionDetails;
import com.github.mpacala00.recipeswebservice.repository.NutritionDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NutritionDetailsServiceImpl implements NutritionDetailsService {

    private final NutritionDetailsRepository nutritionDetailsRepository;
    private final NutritionApiService nutritionApiService;
    @Override
    public Optional<NutritionDetails> findById(String id) {
        return nutritionDetailsRepository.findById(id);
    }

    @Override
    public void save(NutritionDetails nutritionDetails) {
        nutritionDetailsRepository.save(nutritionDetails);
    }

    @Override
    public NutritionDetails getDetailsFromApi(List<Ingredient> ingredientList) {
        return nutritionApiService.fetchNutritionDetails(ingredientList);
    }
}
