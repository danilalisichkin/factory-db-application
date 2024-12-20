package com.factory.backend.entities.nosql;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("materials")
public class MongoMaterial {
    @Id
    @Field(name = "_id")
    private ObjectId id;

    @Indexed(unique=true)
    @Field(name = "id")
    private Integer modelId;

    @Field(name = "name")
    private String name;

    @Field(name = "supplier_name")
    private String supplierName;
}
