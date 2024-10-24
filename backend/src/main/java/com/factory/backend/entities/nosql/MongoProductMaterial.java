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
@Document("product_materials")
public class MongoProductMaterial {
    @Id
    @Field(name = "_id")
    private ObjectId id;

    @Indexed(unique=true)
    @Field(name = "id")
    private String modelId;

    @Field(name = "product_sku")
    private Integer productSku;

    @Field(name = "material_sku")
    private Integer materialSku;

    public void generateId() {
        this.modelId = generateId(this.productSku, this.materialSku);
    }

    public static String generateId(Integer productSku, Integer materialSku) {
        return productSku + "-" + materialSku;
    }
}
