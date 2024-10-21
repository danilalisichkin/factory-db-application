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
@Document(collation = "product_materials")
public class MongoProductMaterial {
    @Id
    private String id;

    @Field(name = "product_sku")
    private Integer productSku;

    @Field(name = "material_sku")
    private Integer materialSku;

    public void generateId() {
        this.id = generateId(this.productSku, this.materialSku);
    }

    public static String generateId(Integer productSku, Integer materialSku) {
        return productSku + "-" + materialSku;
    }
}
