package com.factory.backend.services.impl.sql;

import com.factory.backend.core.dto.product.ProductAddingDTO;
import com.factory.backend.core.dto.product.ProductDTO;
import com.factory.backend.core.mappers.product.ProductMapper;
import com.factory.backend.entities.sql.Product;
import com.factory.backend.exceptions.ResourceNotFoundException;
import com.factory.backend.repository.sql.CategoryRepository;
import com.factory.backend.repository.sql.ProductRepository;
import com.factory.backend.services.IProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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
        if (!productRepository.existsById(productDTO.getId())) {
            throw new ResourceNotFoundException("product with id=%s not found", productDTO.getId());
        }

        return productMapper.entityToDto(productRepository.save(populateProduct(productDTO)));
    }

    @Override
    public void deleteProductById(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("product with id=%s not found", id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public void deleteAllProducts() {
        if (productRepository.count() == 0) {
            throw new ResourceNotFoundException("no products found");
        } else {
            productRepository.deleteAll();
        }
    }

    private Product populateProduct(ProductDTO productDTO) {
        Product product = productMapper.dtoToEntity(productDTO);
        product.setCategory(productDTO.getCategoryId() != null
                ? categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "category with id=%s not found", productDTO.getCategoryId())
                    )
                : null);

        return product;
    }

    private Product populateProduct(ProductAddingDTO productDTO) {
        Product product = productMapper.addingDtoToEntity(productDTO);
        product.setCategory(productDTO.getCategoryId() != null
                ? categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "category with id=%s not found", productDTO.getCategoryId())
                    )
                : null);

        return product;
    }
}
