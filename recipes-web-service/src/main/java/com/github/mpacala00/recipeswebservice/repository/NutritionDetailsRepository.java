package com.github.mpacala00.recipeswebservice.repository;

import com.github.mpacala00.recipeswebservice.model.NutritionDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NutritionDetailsRepository extends MongoRepository<NutritionDetails, String> {
}
