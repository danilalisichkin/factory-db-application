package com.factory.backend.entities.nosql;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("identifiers")
public class Identifier {
    @Id
    private ObjectId id;

    @Indexed(unique=true)
    private Integer categoryId;

    @Indexed(unique=true)
    private Integer materialId;

    @Indexed(unique=true)
    private Integer productId;
}
