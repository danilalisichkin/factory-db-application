package com.factory.backend.core.mappers.material;

import com.factory.backend.core.dto.material.MaterialAddingDTO;
import com.factory.backend.core.dto.material.MaterialDTO;
import com.factory.backend.entities.nosql.MongoMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MongoMaterialMapper {
    @Mapping(source = "modelId", target = "id")
    MaterialDTO entityToDto(MongoMaterial material);

    @Mapping(source = "id", target = "modelId")
    @Mapping(target = "id", ignore = true)
    MongoMaterial dtoToEntity(MaterialDTO dto);

    MongoMaterial addingDtoToEntity(MaterialAddingDTO dto);
}
