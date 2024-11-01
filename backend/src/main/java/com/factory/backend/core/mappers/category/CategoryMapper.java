package com.factory.backend.core.mappers.category;

import com.factory.backend.core.dto.category.CategoryAddingDTO;
import com.factory.backend.core.dto.category.CategoryDTO;
import com.factory.backend.entities.nosql.MongoCategory;
import com.factory.backend.entities.sql.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(source = "parent.id", target = "parentId")
    CategoryDTO entityToDto(Category category);

    @Mapping(source = "parentId", target = "parent.id")
    Category dtoToEntity(CategoryDTO dto);

    @Mapping(source = "parentId", target = "parent.id")
    Category addingDtoToEntity(CategoryAddingDTO dto);

    @Mapping(source = "id", target = "modelId")
    @Mapping(source = "parent.id", target = "parentId")
    MongoCategory entityToMongo(Category entity);
}
