package com.factory.backend.core.dto.category;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class CategoryDTO {
    private Integer id;
    private String name;
    private Integer parentId;
}
