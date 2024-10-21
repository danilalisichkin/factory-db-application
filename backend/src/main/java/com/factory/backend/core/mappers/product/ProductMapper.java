package com.factory.backend.core.mappers.product;

import com.factory.backend.core.dto.product.ProductAddingDTO;
import com.factory.backend.core.dto.product.ProductDTO;
import com.factory.backend.entities.sql.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDTO entityToDto(Product productOrder);

    @Mapping(source = "categoryId", target = "category.id")
    Product dtoToEntity(ProductDTO dto);

    @Mapping(source = "categoryId", target = "category.id")
    Product addingDtoToEntity(ProductAddingDTO dto);
}