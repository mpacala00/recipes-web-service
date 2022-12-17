package com.github.mpacala00.recipeswebservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("unitsOfMeasure")
@Data
@Builder
public class UnitOfMeasure {

    @Id
    private String id;
    private String unit;
}
