package com.factory.backend.services.impl;

import com.factory.backend.core.dto.product.ProductAddingDTO;
import com.factory.backend.core.dto.product.ProductDTO;
import com.factory.backend.core.mappers.product.ProductMapper;
import com.factory.backend.entities.Product;
import com.factory.backend.exceptions.ResourceNotFoundException;
import com.factory.backend.repository.ProductRepository;
import com.factory.backend.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
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
                productRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("Product with id=%s not found", id)
                )
        );
    }

    @Override
    public ProductDTO saveProduct(ProductAddingDTO productDTO) {
        Product product = productRepository.save(productMapper.addingDtoToEntity(productDTO));

        return productMapper.entityToDto(product);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        if (productRepository.findById(productDTO.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Product with id=%s not found", productDTO.getId());
        }

        return productMapper.entityToDto(
                productRepository.save(productMapper.dtoToEntity(productDTO))
        );
    }

    @Override
    public void deleteProductById(Integer id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Product with id=%s not found", id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public void deleteAllProducts() {
        if (productRepository.count() == 0) {
            throw new ResourceNotFoundException("No products found");
        } else {
            productRepository.deleteAll();
        }
    }
}
