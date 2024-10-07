package com.factory.backend.core.dto.product.order;

public class ProductOrderAddingDTO extends ProductOrderDTO {
    public ProductOrderAddingDTO(String clientPhoneNumber, Integer productSku, Integer quantity) {
        super(clientPhoneNumber, productSku, quantity);
    }
}
