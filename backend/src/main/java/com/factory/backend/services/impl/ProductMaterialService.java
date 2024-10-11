package com.factory.backend.services.impl;

import com.factory.backend.core.dto.product.material.ProductMaterialAddingDTO;
import com.factory.backend.core.dto.product.material.ProductMaterialDTO;
import com.factory.backend.core.mappers.product.material.ProductMaterialMapper;
import com.factory.backend.entities.ProductMaterial;
import com.factory.backend.entities.ProductMaterialId;
import com.factory.backend.exceptions.ResourceNotFoundException;
import com.factory.backend.repository.MaterialRepository;
import com.factory.backend.repository.ProductMaterialRepository;
import com.factory.backend.repository.ProductRepository;
import com.factory.backend.services.IProductMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMaterialService implements IProductMaterialService {

    private final ProductMaterialRepository productMaterialRepository;

    private final ProductRepository productRepository;

    private final MaterialRepository materialRepository;

    private final ProductMaterialMapper productMaterialMapper;

    @Autowired
    public ProductMaterialService(ProductMaterialRepository productMaterialRepository, ProductRepository productRepository, MaterialRepository materialRepository, ProductMaterialMapper productMaterialMapper) {
        this.productMaterialRepository = productMaterialRepository;
        this.productRepository = productRepository;
        this.materialRepository = materialRepository;
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
                        () -> new ResourceNotFoundException("Product material with product_sku=%s and material_sku=%s not found", productId, materialId)
                )
        );
    }

    @Override
    public ProductMaterialDTO saveProductMaterial(ProductMaterialAddingDTO productMaterialDTO) {
        return productMaterialMapper.entityToDto(productMaterialRepository.save(populateProductMaterial(productMaterialDTO)));
    }

    @Override
    public ProductMaterialDTO updateProductMaterial(ProductMaterialDTO productMaterialDTO) {
        if (!productMaterialRepository.existsById(new ProductMaterialId(
                        productMaterialDTO.getProductSku(),
                        productMaterialDTO.getMaterialSku()
                )
        )) {
            throw new ResourceNotFoundException("Product material with product_sku=%s and material_sku=%s not found", productMaterialDTO.getProductSku(), productMaterialDTO.getMaterialSku());
        }

        return productMaterialMapper.entityToDto(productMaterialRepository.save(populateProductMaterial(productMaterialDTO)));
    }

    @Override
    public void deleteProductMaterialById(Integer productId, Integer materialId) {
        if (!productMaterialRepository.existsById(new ProductMaterialId(productId, materialId))) {
            throw new ResourceNotFoundException("Product material with product_sku=%s and material_sku=%s not found", productId, materialId);
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

    private ProductMaterial populateProductMaterial(ProductMaterialDTO productMaterialDTO) {
        if (productMaterialDTO.getProductSku() == null)
            throw new ResourceNotFoundException("Product sku is required");
        if (productMaterialDTO.getMaterialSku() == null)
            throw new ResourceNotFoundException("Material sku is required");

        ProductMaterial productMaterial = productMaterialMapper.dtoToEntity(productMaterialDTO);

        productMaterial.setProductSku(
                productMaterialDTO.getProductSku() != null
                        ? productRepository.findById(productMaterialDTO.getProductSku())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Product with sku=%s not found", productMaterialDTO.getProductSku())
                        )
                        : null
        );
        productMaterial.setMaterialSku(
                productMaterialDTO.getMaterialSku() != null
                        ? materialRepository.findById(productMaterialDTO.getMaterialSku())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Material with sku=%s not found", productMaterialDTO.getMaterialSku())
                        )
                        : null
        );

        return productMaterial;
    }
}
