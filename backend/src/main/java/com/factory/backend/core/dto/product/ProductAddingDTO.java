package com.factory.backend.core.dto.product;

import com.factory.backend.entities.Category;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode
public class ProductAddingDTO {
    private String name;
    private BigDecimal price;
    private Category category;
}