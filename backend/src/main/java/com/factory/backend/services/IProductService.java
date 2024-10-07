package com.factory.backend.services;

import com.factory.backend.core.dto.product.ProductAddingDTO;
import com.factory.backend.core.dto.product.ProductDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Integer id);

    ProductDTO saveProduct(ProductAddingDTO productDTO);

    ProductDTO updateProduct(ProductDTO productDTO);

    void deleteProductById(Integer id);

    void deleteAllProducts();
}
