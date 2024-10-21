package com.factory.backend.core.mappers.category;

import com.factory.backend.core.dto.category.CategoryAddingDTO;
import com.factory.backend.core.dto.category.CategoryDTO;
import com.factory.backend.entities.nosql.MongoCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MongoCategoryMapper {
    CategoryDTO entityToDto(MongoCategory category);

    MongoCategory dtoToEntity(CategoryDTO dto);

    MongoCategory addingDtoToEntity(CategoryAddingDTO dto);
}
