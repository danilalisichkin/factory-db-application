package com.factory.backend.services.impl;

import com.factory.backend.core.dto.product.material.ProductMaterialAddingDTO;
import com.factory.backend.core.dto.product.material.ProductMaterialDTO;
import com.factory.backend.core.mappers.product.material.ProductMaterialMapper;
import com.factory.backend.entities.ProductMaterial;
import com.factory.backend.entities.ProductMaterialId;
import com.factory.backend.exceptions.ResourceNotFoundException;
import com.factory.backend.repository.ProductMaterialRepository;
import com.factory.backend.services.IProductMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMaterialService implements IProductMaterialService {

    private final ProductMaterialRepository productMaterialRepository;

    private final ProductMaterialMapper productMaterialMapper;

    @Autowired
    public ProductMaterialService(ProductMaterialRepository productMaterialRepository, ProductMaterialMapper productMaterialMapper) {
        this.productMaterialRepository = productMaterialRepository;
        this.productMaterialMapper = productMaterialMapper;
    }

    @Override
    public List<ProductMaterialDTO> getAllProductMaterials() {
        return productMaterialRepository.findAll().stream()
                .map(productMaterialMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductMaterialDTO getProductMaterialById(Integer productId, Integer materialId) {
        return productMaterialMapper.entityToDto(
                productMaterialRepository.findById(new ProductMaterialId(productId, materialId)).orElseThrow(
                        () -> new ResourceNotFoundException("Product material with product_id=%s and material_id=%s not found", productId, materialId)
                )
        );
    }

    @Override
    public ProductMaterialDTO saveProductMaterial(ProductMaterialAddingDTO productMaterialDTO) {
        ProductMaterial productMaterial = productMaterialRepository.save(productMaterialMapper.addingDtoToEntity(productMaterialDTO));

        return productMaterialMapper.entityToDto(productMaterial);
    }

    @Override
    public ProductMaterialDTO updateProductMaterial(ProductMaterialDTO productMaterialDTO) {
        if (productMaterialRepository.findById(new ProductMaterialId(
                productMaterialDTO.getProductSku(),
                productMaterialDTO.getMaterialSku()
        )).isEmpty()) {
            throw new ResourceNotFoundException("Product material with product_id=%s and material_id=%s not found", productMaterialDTO.getProductSku(), productMaterialDTO.getMaterialSku());
        }

        return productMaterialMapper.entityToDto(
                productMaterialRepository.save(productMaterialMapper.dtoToEntity(productMaterialDTO))
        );
    }

    @Override
    public void deleteProductMaterialById(Integer productId, Integer materialId) {
        if (productMaterialRepository.findById(new ProductMaterialId(productId, materialId)).isEmpty()) {
            throw new ResourceNotFoundException("Product material with product_id=%s and material_id=%s not found", productId, materialId);
        }
        productMaterialRepository.deleteById((new ProductMaterialId(productId, materialId)));
    }

    @Override
    public void deleteAllProductMaterials() {
        if (productMaterialRepository.count() == 0) {
            throw new ResourceNotFoundException("No product materials found");
        } else {
            productMaterialRepository.deleteAll();
        }
    }
}
