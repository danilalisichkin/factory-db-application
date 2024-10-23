package com.factory.backend.entities.nosql;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("products")
public class MongoProduct {
    @Id
    private Integer id;

    @Field(name = "name")
    private String name;

    @Field(name = "price")
    private Double price;

    @Field(name = "category_id")
    private Integer categoryId;
}
