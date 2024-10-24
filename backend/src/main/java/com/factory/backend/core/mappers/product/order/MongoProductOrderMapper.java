package com.factory.backend.core.mappers.product.order;

import com.factory.backend.core.dto.product.order.ProductOrderAddingDTO;
import com.factory.backend.core.dto.product.order.ProductOrderDTO;
import com.factory.backend.entities.nosql.MongoProductOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MongoProductOrderMapper {
    ProductOrderDTO entityToDto(MongoProductOrder productOrder);

    MongoProductOrder dtoToEntity(ProductOrderDTO dto);

    MongoProductOrder addingDtoToEntity(ProductOrderAddingDTO dto);
}
