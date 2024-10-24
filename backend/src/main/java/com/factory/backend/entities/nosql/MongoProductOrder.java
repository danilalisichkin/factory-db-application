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
@Document("product_orders")
public class MongoProductOrder {
    @Id
    @Field(name = "_id")
    private ObjectId id;

    @Indexed(unique=true)
    @Field(name = "id")
    private String modelId;

    @Field(name = "client_phone_number")
    private String clientPhoneNumber;

    @Field(name = "product_sku")
    private Integer productSku;

    @Field(name = "quantity")
    private Integer quantity;

    public void generateId() {
        this.modelId = generateId(this.clientPhoneNumber, this.productSku);
    }

    public static String generateId(String clientPhoneNumber, Integer productSku) {
        return clientPhoneNumber + "-" + productSku;
    }
}
