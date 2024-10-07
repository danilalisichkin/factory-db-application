package com.factory.backend.core.dto.category;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class CategoryAddingDTO {
    private String name;
    private Integer parentId;
}
