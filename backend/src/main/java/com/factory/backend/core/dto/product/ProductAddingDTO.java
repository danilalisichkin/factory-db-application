package com.factory.backend.core.dto.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode
public class ProductAddingDTO {
    @NotNull
    @Size(min = 3, max = 100, message = "Name must be a string with length from 3 to 100 characters")
    private String name;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    private Integer categoryId;
}
