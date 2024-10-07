package com.factory.backend.services.impl;

import com.factory.backend.core.dto.product.material.ProductMaterialAddingDTO;
import com.factory.backend.core.dto.product.material.ProductMaterialDTO;
import com.factory.backend.services.IProductMaterialService;

import java.util.List;

public class ProductMaterialService implements IProductMaterialService {
    @Override
    public List<ProductMaterialDTO> getAllProductMaterials() {
        return List.of();
    }

    @Override
    public ProductMaterialDTO getProductMaterialById(Long productId, Long materialId) {
        return null;
    }

    @Override
    public ProductMaterialDTO saveProductMaterial(ProductMaterialAddingDTO productMaterialDTO) {
        return null;
    }

    @Override
    public ProductMaterialDTO updateProductMaterial(ProductMaterialDTO productMaterialDTO) {
        return null;
    }

    @Override
    public void deleteProductMaterialById(Integer productId, Integer materialId) {

    }

    @Override
    public void deleteAllProductMaterials() {

    }
}
