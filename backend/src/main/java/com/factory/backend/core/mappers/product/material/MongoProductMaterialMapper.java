package com.factory.backend.core.mappers.product.material;

import com.factory.backend.core.dto.product.material.ProductMaterialAddingDTO;
import com.factory.backend.core.dto.product.material.ProductMaterialDTO;
import com.factory.backend.entities.nosql.MongoProductMaterial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MongoProductMaterialMapper {
    ProductMaterialDTO entityToDto(MongoProductMaterial productMaterial);

    MongoProductMaterial dtoToEntity(ProductMaterialDTO dto);

    MongoProductMaterial addingDtoToEntity(ProductMaterialAddingDTO dto);
}
