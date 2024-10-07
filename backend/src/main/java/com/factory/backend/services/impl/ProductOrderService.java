package com.factory.backend.services.impl;

import com.factory.backend.core.dto.product.order.ProductOrderAddingDTO;
import com.factory.backend.core.dto.product.order.ProductOrderDTO;
import com.factory.backend.services.IProductOrderService;

import java.util.List;

public class ProductOrderService implements IProductOrderService {
    @Override
    public List<ProductOrderDTO> getAllProductOrders() {
        return List.of();
    }

    @Override
    public ProductOrderDTO getProductOrderById(Long productId, String clientPhone) {
        return null;
    }

    @Override
    public ProductOrderDTO saveProductOrder(ProductOrderAddingDTO ProductOrderDTO) {
        return null;
    }

    @Override
    public ProductOrderDTO updateProductOrder(ProductOrderDTO ProductOrderDTO) {
        return null;
    }

    @Override
    public void deleteProductOrderById(Integer productId, String clientPhone) {

    }

    @Override
    public void deleteAllProductOrders() {

    }
}
