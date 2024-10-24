package com.factory.backend.services.impl.nosql;

import com.factory.backend.core.dto.product.order.ProductOrderAddingDTO;
import com.factory.backend.core.dto.product.order.ProductOrderDTO;
import com.factory.backend.core.mappers.product.order.MongoProductOrderMapper;
import com.factory.backend.entities.nosql.MongoProductOrder;
import com.factory.backend.exceptions.BadRequestException;
import com.factory.backend.exceptions.ResourceNotFoundException;
import com.factory.backend.repository.nosql.MongoClientRepository;
import com.factory.backend.repository.nosql.MongoProductOrderRepository;
import com.factory.backend.repository.nosql.MongoProductRepository;
import com.factory.backend.services.IProductOrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("mongoProductOrderService")
public class MongoProductOrderService implements IProductOrderService {

    private final MongoProductOrderRepository productOrderRepository;

    private final MongoClientRepository clientRepository;

    private final MongoProductRepository productRepository;

    private final MongoProductOrderMapper productOrderMapper;

    @Autowired
    public MongoProductOrderService(
            MongoProductOrderRepository productOrderRepository,
            MongoClientRepository clientRepository,
            MongoProductRepository productRepository,
            MongoProductOrderMapper productOrderMapper
    ) {
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
                productOrderRepository.findByModelId(MongoProductOrder.generateId(clientPhone, productId)).orElseThrow(
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
        if (!productOrderRepository.existsByModelId(MongoProductOrder.generateId(
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
        if (!productOrderRepository.existsByModelId(MongoProductOrder.generateId(clientPhone, productId))) {
            throw new ResourceNotFoundException("product order with client_phone=%s and product_sku=%s not found", clientPhone, productId);
        }
        productOrderRepository.deleteByModelId(MongoProductOrder.generateId(clientPhone, productId));
    }

    @Override
    public void deleteAllProductOrders() {
        if (productOrderRepository.count() == 0) {
            throw new ResourceNotFoundException("no product orders found");
        } else {
            productOrderRepository.deleteAll();
        }
    }

    private MongoProductOrder populateProductOrder(ProductOrderDTO productOrderDTO) {
        final String clientPhone = productOrderDTO.getClientPhoneNumber();
        final Integer productSku = productOrderDTO.getProductSku();

        if (clientPhone == null || clientPhone.isBlank())
            throw new BadRequestException("client phone is required");
        if (productSku == null)
            throw new BadRequestException("product sku is required");

        if (!clientRepository.existsByPhoneNumber(clientPhone))
            throw new ResourceNotFoundException("client with phone=%s not found", clientPhone);
        if (!productRepository.existsByModelId(productSku))
            throw new ResourceNotFoundException("product with sku=%s not found", productSku);

        MongoProductOrder productOrder = productOrderMapper.dtoToEntity(productOrderDTO);
        productOrder.generateId();

        return productOrder;
    }
}
