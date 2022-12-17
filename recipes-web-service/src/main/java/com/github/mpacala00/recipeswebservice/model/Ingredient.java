package com.github.mpacala00.recipeswebservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("ingredients")
@Data
@Builder
public class Ingredient {

    @Id
    private String id;
    private String name;
    private Double quantity;

    private UnitOfMeasure unit;

}
