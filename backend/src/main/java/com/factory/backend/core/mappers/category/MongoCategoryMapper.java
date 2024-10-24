package com.factory.backend.core.mappers.category;

import com.factory.backend.core.dto.category.CategoryAddingDTO;
import com.factory.backend.core.dto.category.CategoryDTO;
import com.factory.backend.entities.nosql.MongoCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MongoCategoryMapper {
    @Mapping(source = "modelId", target = "id")
    CategoryDTO entityToDto(MongoCategory category);

    @Mapping(source = "id", target = "modelId")
    @Mapping(target = "id", ignore = true)
    MongoCategory dtoToEntity(CategoryDTO dto);

    MongoCategory addingDtoToEntity(CategoryAddingDTO dto);
}
