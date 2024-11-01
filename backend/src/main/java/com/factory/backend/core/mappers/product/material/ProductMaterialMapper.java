package com.factory.backend.core.mappers.product.material;

import com.factory.backend.core.dto.product.material.ProductMaterialAddingDTO;
import com.factory.backend.core.dto.product.material.ProductMaterialDTO;
import com.factory.backend.entities.nosql.MongoProductMaterial;
import com.factory.backend.entities.sql.ProductMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMaterialMapper {
    @Mapping(source = "productSku.id", target = "productSku")
    @Mapping(source = "materialSku.id", target = "materialSku")
    ProductMaterialDTO entityToDto(ProductMaterial productMaterial);

    @Mapping(source = "productSku", target = "productSku.id")
    @Mapping(source = "materialSku", target = "materialSku.id")
    ProductMaterial dtoToEntity(ProductMaterialDTO dto);

    @Mapping(source = "productSku", target = "productSku.id")
    @Mapping(source = "materialSku", target = "materialSku.id")
    ProductMaterial addingDtoToEntity(ProductMaterialAddingDTO dto);

    MongoProductMaterial entityToMongo(ProductMaterial entity);
}

