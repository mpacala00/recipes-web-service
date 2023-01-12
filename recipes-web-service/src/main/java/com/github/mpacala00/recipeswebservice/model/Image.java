package com.github.mpacala00.recipeswebservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Data
@AllArgsConstructor
public class Image {

    private String name;
    private String type;
    private Binary data;

    public static Image createImage(MultipartFile multipartFile) {
        try {
            return new Image(multipartFile.getName(), multipartFile.getContentType(),
                    new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
        } catch (IOException ioe) {
            throw new RuntimeException("An error occured while reading image");
        }
    }
}
