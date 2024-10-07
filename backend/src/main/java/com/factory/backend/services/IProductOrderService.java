package com.factory.backend.services;

import com.factory.backend.core.dto.product.order.ProductOrderAddingDTO;
import com.factory.backend.core.dto.product.order.ProductOrderDTO;

import java.util.List;

public interface IProductOrderService {
    List<ProductOrderDTO> getAllProductOrders();

    ProductOrderDTO getProductOrderById(Long productId, String clientPhone);

    ProductOrderDTO saveProductOrder(ProductOrderAddingDTO ProductOrderDTO);

    ProductOrderDTO updateProductOrder(ProductOrderDTO ProductOrderDTO);

    void deleteProductOrderById(Integer productId, String clientPhone);

    void deleteAllProductOrders();
}
