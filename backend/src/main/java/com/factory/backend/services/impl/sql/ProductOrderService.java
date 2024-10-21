package com.factory.backend.services.impl.sql;

import com.factory.backend.core.dto.product.order.ProductOrderAddingDTO;
import com.factory.backend.core.dto.product.order.ProductOrderDTO;
import com.factory.backend.core.mappers.product.order.ProductOrderMapper;
import com.factory.backend.entities.sql.ProductOrder;
import com.factory.backend.entities.sql.ProductOrderId;
import com.factory.backend.exceptions.BadRequestException;
import com.factory.backend.exceptions.ResourceNotFoundException;
import com.factory.backend.repository.sql.ClientRepository;
import com.factory.backend.repository.sql.ProductOrderRepository;
import com.factory.backend.repository.sql.ProductRepository;
import com.factory.backend.services.IProductOrderService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
                        () -> new ResourceNotFoundException("product order with client_phone=%s and product_sku=%s not found", clientPhone, productId)
                )
        );
    }

    @Override
    @Transactional
    public ProductOrderDTO saveProductOrder(ProductOrderAddingDTO productOrderDTO) {
        return productOrderMapper.entityToDto(
                productOrderRepository.save(
                        populateProductOrder(productOrderDTO)
                )
        );
    }

    @Override
    @Transactional
    public ProductOrderDTO updateProductOrder(ProductOrderDTO productOrderDTO) {
        if (!productOrderRepository.existsById(new ProductOrderId(
                        productOrderDTO.getClientPhoneNumber(),
                        productOrderDTO.getProductSku()
                )
        )) {
            throw new ResourceNotFoundException("product order with client_phone=%s and product_sku=%s not found", productOrderDTO.getClientPhoneNumber(), productOrderDTO.getProductSku());
        }

        return productOrderMapper.entityToDto(productOrderRepository.save(populateProductOrder(productOrderDTO)));
    }

    @Override
    public void deleteProductOrderById(String clientPhone, Integer productId) {
        if (!productOrderRepository.existsById(new ProductOrderId(clientPhone, productId))) {
            throw new ResourceNotFoundException("product order with client_phone=%s and product_sku=%s not found", clientPhone, productId);
        }
        productOrderRepository.deleteById((new ProductOrderId(clientPhone, productId)));
    }

    @Override
    public void deleteAllProductOrders() {
        if (productOrderRepository.count() == 0) {
            throw new ResourceNotFoundException("no product orders found");
        } else {
            productOrderRepository.deleteAll();
        }
    }

    private ProductOrder populateProductOrder(ProductOrderDTO productOrderDTO) {
        String clientPhone = productOrderDTO.getClientPhoneNumber();
        Integer productSku = productOrderDTO.getProductSku();

        if (clientPhone == null || clientPhone.isBlank())
            throw new BadRequestException("client phone is required");
        if (productSku == null)
            throw new BadRequestException("product sku is required");

        ProductOrder productOrder = productOrderMapper.dtoToEntity(productOrderDTO);

        productOrder.setClientPhoneNumber(
                clientRepository.findById(clientPhone)
                        .orElseThrow(() -> new ResourceNotFoundException("client with phone=%s not found", clientPhone))
        );
        productOrder.setProductSku(
                productRepository.findById(productSku)
                        .orElseThrow(() -> new ResourceNotFoundException("product with sku=%s not found", productSku))
        );
        productOrder.setId(new ProductOrderId(clientPhone, productSku));

        return productOrder;
    }
}
