package com.factory.backend.core.dto.product.material;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class ProductMaterialDTO {
    @NotNull
    private Integer productSku;

    @NotNull
    private Integer materialSku;
}
