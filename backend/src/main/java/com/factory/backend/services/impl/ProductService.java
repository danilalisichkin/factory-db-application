package com.factory.backend.services.impl;

import com.factory.backend.core.dto.product.ProductAddingDTO;
import com.factory.backend.core.dto.product.ProductDTO;
import com.factory.backend.services.IProductService;

import java.util.List;

public class ProductService implements IProductService {
    @Override
    public List<ProductDTO> getAllProducts() {
        return List.of();
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return null;
    }

    @Override
    public ProductDTO saveProduct(ProductAddingDTO productDTO) {
        return null;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        return null;
    }

    @Override
    public void deleteProductById(Integer id) {

    }

    @Override
    public void deleteAllProducts() {

    }
}
