package com.factory.backend.core.mappers.product;

import com.factory.backend.core.dto.product.ProductAddingDTO;
import com.factory.backend.core.dto.product.ProductDTO;
import com.factory.backend.entities.nosql.MongoProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MongoProductMapper {
    ProductDTO entityToDto(MongoProduct productOrder);

    MongoProduct dtoToEntity(ProductDTO dto);

    MongoProduct addingDtoToEntity(ProductAddingDTO dto);
}
