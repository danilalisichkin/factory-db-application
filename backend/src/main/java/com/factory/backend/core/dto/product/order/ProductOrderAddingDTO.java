package com.factory.backend.core.dto.product.order;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entry to add a new record to \"product_orders\" table")
public class ProductOrderAddingDTO extends ProductOrderDTO {
    public ProductOrderAddingDTO(String clientPhoneNumber, Integer productSku, Integer quantity) {
        super(clientPhoneNumber, productSku, quantity);
    }
}
