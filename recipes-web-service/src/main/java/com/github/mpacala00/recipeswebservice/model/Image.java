package com.github.mpacala00.recipeswebservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.Binary;


@Data
@AllArgsConstructor
public class Image {

    private String name;
    private String type;
    private Binary data;
}
