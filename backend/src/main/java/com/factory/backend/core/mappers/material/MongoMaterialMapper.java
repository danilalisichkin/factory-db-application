package com.factory.backend.core.mappers.material;

import com.factory.backend.core.dto.material.MaterialAddingDTO;
import com.factory.backend.core.dto.material.MaterialDTO;
import com.factory.backend.entities.nosql.MongoMaterial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MongoMaterialMapper {
    MaterialDTO entityToDto(MongoMaterial material);

    MongoMaterial dtoToEntity(MaterialDTO dto);

    MongoMaterial addingDtoToEntity(MaterialAddingDTO dto);
}
