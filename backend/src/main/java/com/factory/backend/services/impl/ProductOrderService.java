package com.factory.backend.services.impl;

import com.factory.backend.core.dto.product.order.ProductOrderAddingDTO;
import com.factory.backend.core.dto.product.order.ProductOrderDTO;
import com.factory.backend.core.mappers.product.order.ProductOrderMapper;
import com.factory.backend.entities.ProductOrder;
import com.factory.backend.entities.ProductOrderId;
import com.factory.backend.exceptions.BadRequestException;
import com.factory.backend.exceptions.ResourceNotFoundException;
import com.factory.backend.repository.ClientRepository;
import com.factory.backend.repository.ProductOrderRepository;
import com.factory.backend.repository.ProductRepository;
import com.factory.backend.services.IProductOrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductOrderService implements IProductOrderService {

    private final ProductOrderRepository productOrderRepository;

    private final ClientRepository clientRepository;

    private final ProductRepository productRepository;

    private final ProductOrderMapper productOrderMapper;

    @Autowired
    public ProductOrderService(ProductOrderRepository productOrderRepository, ClientRepository clientRepository, ProductRepository productRepository, ProductOrderMapper productOrderMapper) {
        this.productOrderRepository = productOrderRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
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
                        () -> new ResourceNotFoundException("Product order with client_phone=%s and product_sku=%s not found", clientPhone, productId)
                )
        );
    }

    @Override
    @Transactional
    public ProductOrderDTO saveProductOrder(ProductOrderAddingDTO productOrderDTO) {
        return productOrderMapper.entityToDto(productOrderRepository.save(populateProductOrder(productOrderDTO)));
    }

    @Override
    @Transactional
    public ProductOrderDTO updateProductOrder(ProductOrderDTO productOrderDTO) {
        if (!productOrderRepository.existsById(new ProductOrderId(
                        productOrderDTO.getClientPhoneNumber(),
                        productOrderDTO.getProductSku()
                )
        )) {
            throw new ResourceNotFoundException("Product order with client_phone=%s and product_sku=%s not found", productOrderDTO.getClientPhoneNumber(), productOrderDTO.getProductSku());
        }

        return productOrderMapper.entityToDto(productOrderRepository.save(populateProductOrder(productOrderDTO)));
    }

    @Override
    public void deleteProductOrderById(String clientPhone, Integer productId) {
        if (!productOrderRepository.existsById(new ProductOrderId(clientPhone, productId))) {
            throw new ResourceNotFoundException("Product order with client_phone=%s and product_sku=%s not found", clientPhone, productId);
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

    private ProductOrder populateProductOrder(ProductOrderDTO productOrderDTO) {
        if (productOrderDTO.getProductSku() == null)
            throw new BadRequestException("Product sku is required");
        if (productOrderDTO.getClientPhoneNumber() == null || productOrderDTO.getClientPhoneNumber().isBlank())
            throw new BadRequestException("Product sku is required");

        ProductOrder productOrder = productOrderMapper.dtoToEntity(productOrderDTO);

        productOrder.setClientPhoneNumber(
                clientRepository.findById(productOrderDTO.getClientPhoneNumber())
                        .orElseThrow(() -> new ResourceNotFoundException("Client with phone=%s not found", productOrderDTO.getClientPhoneNumber()))
        );
        productOrder.setProductSku(
                productRepository.findById(productOrderDTO.getProductSku())
                        .orElseThrow(() -> new ResourceNotFoundException("Product with sku=%s not found", productOrderDTO.getProductSku()))
        );

        return productOrder;
    }
}
