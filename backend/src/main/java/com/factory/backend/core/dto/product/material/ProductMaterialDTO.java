package com.factory.backend.core.dto.product.material;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class ProductMaterialDTO {
    private Integer productSku;
    private Integer materialSku;
}
