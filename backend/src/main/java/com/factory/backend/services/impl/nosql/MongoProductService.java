package com.factory.backend.services.impl.nosql;

import com.factory.backend.core.dto.product.ProductAddingDTO;
import com.factory.backend.core.dto.product.ProductDTO;
import com.factory.backend.core.mappers.product.MongoProductMapper;
import com.factory.backend.entities.nosql.MongoProduct;
import com.factory.backend.exceptions.ResourceNotFoundException;
import com.factory.backend.repository.nosql.MongoCategoryRepository;
import com.factory.backend.repository.nosql.MongoProductRepository;
import com.factory.backend.services.IIdentifierGenerationService;
import com.factory.backend.services.IProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("mongoProductService")
public class MongoProductService implements IProductService {

    private final MongoProductRepository productRepository;

    private final MongoCategoryRepository categoryRepository;

    private final MongoProductMapper productMapper;

    private final IIdentifierGenerationService identifierGenerationService;

    @Autowired
    public MongoProductService(
            MongoProductRepository productRepository,
            MongoCategoryRepository categoryRepository,
            MongoProductMapper productMapper, IIdentifierGenerationService identifierGenerationService
    ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
        this.identifierGenerationService = identifierGenerationService;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Integer id) {
        return productMapper.entityToDto(
                productRepository.findByModelId(id).orElseThrow(
                        () -> new ResourceNotFoundException("product with id=%s not found", id)
                )
        );
    }

    @Override
    @Transactional
    public ProductDTO saveProduct(ProductAddingDTO productDTO) {
        return productMapper.entityToDto(productRepository.save(populateProduct(productDTO)));
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(ProductDTO productDTO) {
        if (!productRepository.existsByModelId(productDTO.getId())) {
            throw new ResourceNotFoundException("product with id=%s not found", productDTO.getId());
        }

        return productMapper.entityToDto(productRepository.save(populateProduct(productDTO)));
    }

    @Override
    public void deleteProductById(Integer id) {
        if (!productRepository.existsByModelId(id)) {
            throw new ResourceNotFoundException("product with id=%s not found", id);
        }
        productRepository.deleteByModelId(id);
    }

    @Override
    public void deleteAllProducts() {
        if (productRepository.count() == 0) {
            throw new ResourceNotFoundException("no products found");
        } else {
            productRepository.deleteAll();
        }
    }

    private MongoProduct populateProduct(ProductDTO productDTO) {
        if (!categoryRepository.existsByModelId(productDTO.getCategoryId()))
            throw new ResourceNotFoundException("category with id=%s not found", productDTO.getCategoryId());

        MongoProduct product = productMapper.dtoToEntity(productDTO);

        return product;
    }

    private MongoProduct populateProduct(ProductAddingDTO productDTO) {
        final Integer categoryId = productDTO.getCategoryId();

        if (categoryId != null && !categoryRepository.existsByModelId(productDTO.getCategoryId()))
            throw new ResourceNotFoundException("category with id=%s not found", productDTO.getCategoryId());

        MongoProduct product = productMapper.addingDtoToEntity(productDTO);
        product.setModelId(identifierGenerationService.generateProductIdentifier());

        return product;
    }
}
