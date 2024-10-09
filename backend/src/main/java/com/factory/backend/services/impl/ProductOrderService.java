package com.factory.backend.services.impl;

import com.factory.backend.core.dto.product.order.ProductOrderAddingDTO;
import com.factory.backend.core.dto.product.order.ProductOrderDTO;
import com.factory.backend.core.mappers.product.order.ProductOrderMapper;
import com.factory.backend.entities.ProductOrder;
import com.factory.backend.entities.ProductOrderId;
import com.factory.backend.exceptions.ResourceNotFoundException;
import com.factory.backend.repository.ProductOrderRepository;
import com.factory.backend.services.IProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductOrderService implements IProductOrderService {

    private final ProductOrderRepository productOrderRepository;

    private final ProductOrderMapper productOrderMapper;

    @Autowired
    public ProductOrderService(ProductOrderRepository productOrderRepository, ProductOrderMapper productOrderMapper) {
        this.productOrderRepository = productOrderRepository;
        this.productOrderMapper = productOrderMapper;
    }

    @Override
    public List<ProductOrderDTO> getAllProductOrders() {
        return productOrderRepository.findAll().stream()
                .map(productOrderMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductOrderDTO getProductOrderById(String clientPhone, Integer productId) {
        return productOrderMapper.entityToDto(
                productOrderRepository.findById(new ProductOrderId(clientPhone, productId)).orElseThrow(
                        () -> new ResourceNotFoundException("Product order with client_phone=%s and product_id=%s not found", clientPhone, productId)
                )
        );
    }

    @Override
    public ProductOrderDTO saveProductOrder(ProductOrderAddingDTO ProductOrderDTO) {
        ProductOrder productMaterial = productOrderRepository.save(productOrderMapper.addingDtoToEntity(ProductOrderDTO));

        return productOrderMapper.entityToDto(productMaterial);
    }

    @Override
    public ProductOrderDTO updateProductOrder(ProductOrderDTO ProductOrderDTO) {
        if (productOrderRepository.findById(new ProductOrderId(
                ProductOrderDTO.getClientPhoneNumber(),
                ProductOrderDTO.getProductSku()
        )).isEmpty()) {
            throw new ResourceNotFoundException("Product order with client_phone=%s and product_id=%s not found", ProductOrderDTO.getClientPhoneNumber(), ProductOrderDTO.getProductSku());
        }

        return productOrderMapper.entityToDto(
                productOrderRepository.save(productOrderMapper.dtoToEntity(ProductOrderDTO))
        );
    }

    @Override
    public void deleteProductOrderById(String clientPhone, Integer productId) {
        if (productOrderRepository.findById(new ProductOrderId(clientPhone, productId)).isEmpty()) {
            throw new ResourceNotFoundException("Product order with client_phone=%s and product_id=%s not found", clientPhone, productId);
        }
        productOrderRepository.deleteById((new ProductOrderId(clientPhone, productId)));
    }

    @Override
    public void deleteAllProductOrders() {
        if (productOrderRepository.count() == 0) {
            throw new ResourceNotFoundException("No product orders found");
        } else {
            productOrderRepository.deleteAll();
        }
    }
}
