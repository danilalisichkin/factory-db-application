package com.factory.backend.entities.nosql;

import com.factory.backend.entities.sql.Material;
import com.factory.backend.entities.sql.Product;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
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
public class ProductMaterial {
    @Id
    private String id;

    @Field(name = "product_sku")
    private Integer productSku;

    @Field(name = "material_sku")
    private Integer materialSku;

    public void generateId() {
        this.id = productSku + "-" + materialSku;
    }
}
