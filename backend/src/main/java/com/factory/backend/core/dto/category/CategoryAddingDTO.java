package com.factory.backend.core.dto.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class CategoryAddingDTO {
    @NotNull
    @Size(min = 3, max = 100, message = "Name must be a string with length from 3 to 100 characters")
    private String name;

    private Integer parentId;
}
