package com.factory.backend.core.mappers.material;

import com.factory.backend.core.dto.material.MaterialAddingDTO;
import com.factory.backend.core.dto.material.MaterialDTO;
import com.factory.backend.entities.nosql.MongoMaterial;
import com.factory.backend.entities.sql.Material;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MaterialMapper {
    MaterialDTO entityToDto(Material material);

    Material dtoToEntity(MaterialDTO dto);

    Material addingDtoToEntity(MaterialAddingDTO dto);

    @Mapping(source = "id", target = "modelId")
    @Mapping(target = "id", ignore = true)
    MongoMaterial entityToMongo(Material entity);
}
