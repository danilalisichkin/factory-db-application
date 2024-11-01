package com.factory.backend.core.mappers.product.order;

import com.factory.backend.core.dto.product.order.ProductOrderAddingDTO;
import com.factory.backend.core.dto.product.order.ProductOrderDTO;
import com.factory.backend.entities.nosql.MongoProductMaterial;
import com.factory.backend.entities.nosql.MongoProductOrder;
import com.factory.backend.entities.sql.ProductOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductOrderMapper {
    @Mapping(source = "clientPhoneNumber.phoneNumber", target = "clientPhoneNumber")
    @Mapping(source = "productSku.id", target = "productSku")
    ProductOrderDTO entityToDto(ProductOrder productOrder);

    @Mapping(source = "clientPhoneNumber", target = "clientPhoneNumber.phoneNumber")
    @Mapping(source = "productSku", target = "productSku.id")
    ProductOrder dtoToEntity(ProductOrderDTO dto);

    @Mapping(source = "clientPhoneNumber", target = "clientPhoneNumber.phoneNumber")
    @Mapping(source = "productSku", target = "productSku.id")
    ProductOrder addingDtoToEntity(ProductOrderAddingDTO dto);

    default MongoProductOrder entityToMongo(ProductOrder entity) {
        MongoProductOrder mongoProductOrder = new MongoProductOrder();

        mongoProductOrder.setProductSku(entity.getProductSku().getId());
        mongoProductOrder.setClientPhoneNumber(entity.getClientPhoneNumber().getPhoneNumber());
        mongoProductOrder.setQuantity(entity.getQuantity());
        mongoProductOrder.generateId();

        return mongoProductOrder;
    }
}