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
@Document(collation = "product_orders")
public class ProductOrder {
    @Id
    private String id;

    @Field(name = "client_phone_number")
    private String clientPhoneNumber;

    @Field(name = "product_sku")
    private Integer productSku;

    @Field(name = "quantity")
    private Integer quantity;

    public void generateId() {
        this.id = clientPhoneNumber + "-" + productSku;
    }
}
