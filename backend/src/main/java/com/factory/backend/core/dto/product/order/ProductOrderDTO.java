package com.factory.backend.core.dto.product.order;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class ProductOrderDTO {
    private String clientPhoneNumber;
    private Integer productSku;
    private Integer quantity;
}
