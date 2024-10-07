package com.factory.backend.core.mappers.product;

import com.factory.backend.core.dto.product.ProductAddingDTO;
import com.factory.backend.core.dto.product.ProductDTO;
import com.factory.backend.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO entityToDto(Product productOrder);

    Product dtoToEntity(ProductDTO dto);

    Product addingDtoToEntity(ProductAddingDTO dto);
}