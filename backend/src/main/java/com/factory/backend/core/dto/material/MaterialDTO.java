package com.factory.backend.core.dto.material;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class MaterialDTO {
    @NotNull
    @Size(min = 3, max = 100, message = "Name must be a string with length from 3 to 100 characters")
    private Integer id;

    @NotNull
    @Size(min = 3, max = 100, message = "Name must be a string with length from 3 to 100 characters")
    private String name;

    @NotNull
    @Size(min = 3, max = 100, message = "Name must be a string with length from 3 to 100 characters")
    private String supplierName;
}
