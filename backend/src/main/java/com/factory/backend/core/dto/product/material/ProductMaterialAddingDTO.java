package com.factory.backend.core.dto.product.material;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entry to add a new record to \"product_materials\" table")
public class ProductMaterialAddingDTO extends ProductMaterialDTO {
    public ProductMaterialAddingDTO(Integer productSku, Integer materialSku) {
        super(productSku, materialSku);
    }
}
