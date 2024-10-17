package com.factory.backend.core.dto.product.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
@Schema(description = "Entry to get/update/delete a record in \"product_orders\" table")
public class ProductOrderDTO {
    @NotNull
    @Pattern(regexp = "^375(15|29|33|44)\\d{7}$",
            message = "illegal format of phone number, correct example: 375291234567")
    private String clientPhoneNumber;

    @NotNull
    private Integer productSku;

    @NotNull
    private Integer quantity;
}
