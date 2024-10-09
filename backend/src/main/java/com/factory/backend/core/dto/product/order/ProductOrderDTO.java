package com.factory.backend.core.dto.product.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class ProductOrderDTO {
    @NotNull
    @Pattern(regexp = "^\\+375(15|29|33|44)\\d{7}$",
            message = "illegal format of phone number, correct example: +375291234567")
    private String clientPhoneNumber;

    @NotNull
    private Integer productSku;

    @NotNull
    private Integer quantity;
}
