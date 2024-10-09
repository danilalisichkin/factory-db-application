package com.factory.backend.services;

import com.factory.backend.core.dto.product.order.ProductOrderAddingDTO;
import com.factory.backend.core.dto.product.order.ProductOrderDTO;

import java.util.List;

public interface IProductOrderService {
    List<ProductOrderDTO> getAllProductOrders();

    ProductOrderDTO getProductOrderById(String clientPhone, Integer productId);

    ProductOrderDTO saveProductOrder(ProductOrderAddingDTO ProductOrderDTO);

    ProductOrderDTO updateProductOrder(ProductOrderDTO ProductOrderDTO);

    void deleteProductOrderById(String clientPhone, Integer productId);

    void deleteAllProductOrders();
}
