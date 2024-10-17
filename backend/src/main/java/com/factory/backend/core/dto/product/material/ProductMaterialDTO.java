package com.factory.backend.core.dto.product.material;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode@Schema(description = "Entry to get/update/delete a record in \"product_materials\" table")

public class ProductMaterialDTO {
    @NotNull
    private Integer productSku;

    @NotNull
    private Integer materialSku;
}
