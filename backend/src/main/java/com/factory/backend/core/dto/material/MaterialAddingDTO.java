package com.factory.backend.core.dto.material;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
@Schema(description = "Entry to add a new record to \"materials\" table")
public class MaterialAddingDTO {
    @NotNull
    @Size(min = 3, max = 100, message = "Name must be a string with length from 3 to 100 characters")
    private String name;

    @NotNull
    @Size(min = 3, max = 100, message = "Supplier name must be a string with length from 3 to 100 characters")
    private String supplierName;
}
