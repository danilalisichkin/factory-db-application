package com.factory.backend.core.mappers.product;

import com.factory.backend.core.dto.product.ProductAddingDTO;
import com.factory.backend.core.dto.product.ProductDTO;
import com.factory.backend.entities.nosql.MongoProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MongoProductMapper {
    @Mapping(source = "modelId", target = "id")
    ProductDTO entityToDto(MongoProduct productOrder);

    @Mapping(source = "id", target = "modelId")
    @Mapping(target = "id", ignore = true)
    MongoProduct dtoToEntity(ProductDTO dto);

    MongoProduct addingDtoToEntity(ProductAddingDTO dto);
}
