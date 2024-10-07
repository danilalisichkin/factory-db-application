package com.factory.backend.core.dto.material;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class MaterialAddingDTO {
    private String name;
    private String supplierName;
}
