package com.factory.backend.services;

import com.factory.backend.core.dto.product.material.ProductMaterialAddingDTO;
import com.factory.backend.core.dto.product.material.ProductMaterialDTO;

import java.util.List;

public interface IProductMaterialService {
    List<ProductMaterialDTO> getAllProductMaterials();

    ProductMaterialDTO getProductMaterialById(Integer productId, Integer materialId);

    ProductMaterialDTO saveProductMaterial(ProductMaterialAddingDTO productMaterialDTO);

    ProductMaterialDTO updateProductMaterial(ProductMaterialDTO productMaterialDTO);

    void deleteProductMaterialById(Integer productId, Integer materialId);

    void deleteAllProductMaterials();
}
