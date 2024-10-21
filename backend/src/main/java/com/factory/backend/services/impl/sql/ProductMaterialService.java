package com.factory.backend.services.impl.sql;

import com.factory.backend.core.dto.product.material.ProductMaterialAddingDTO;
import com.factory.backend.core.dto.product.material.ProductMaterialDTO;
import com.factory.backend.core.mappers.product.material.ProductMaterialMapper;
import com.factory.backend.entities.sql.ProductMaterial;
import com.factory.backend.entities.sql.ProductMaterialId;
import com.factory.backend.exceptions.ResourceNotFoundException;
import com.factory.backend.repository.sql.MaterialRepository;
import com.factory.backend.repository.sql.ProductMaterialRepository;
import com.factory.backend.repository.sql.ProductRepository;
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
                        () -> new ResourceNotFoundException("product material with product_sku=%s and material_sku=%s not found", productId, materialId)
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
            throw new ResourceNotFoundException("product material with product_sku=%s and material_sku=%s not found", productMaterialDTO.getProductSku(), productMaterialDTO.getMaterialSku());
        }

        return productMaterialMapper.entityToDto(productMaterialRepository.save(populateProductMaterial(productMaterialDTO)));
    }

    @Override
    public void deleteProductMaterialById(Integer productId, Integer materialId) {
        if (!productMaterialRepository.existsById(new ProductMaterialId(productId, materialId))) {
            throw new ResourceNotFoundException("product material with product_sku=%s and material_sku=%s not found", productId, materialId);
        }
        productMaterialRepository.deleteById((new ProductMaterialId(productId, materialId)));
    }

    @Override
    public void deleteAllProductMaterials() {
        if (productMaterialRepository.count() == 0) {
            throw new ResourceNotFoundException("no product materials found");
        } else {
            productMaterialRepository.deleteAll();
        }
    }

    private ProductMaterial populateProductMaterial(ProductMaterialDTO productMaterialDTO) {
        Integer productSku = productMaterialDTO.getProductSku();
        Integer materialSku = productMaterialDTO.getMaterialSku();

        if (productSku == null)
            throw new ResourceNotFoundException("product sku is required");
        if (materialSku == null)
            throw new ResourceNotFoundException("material sku is required");

        ProductMaterial productMaterial = productMaterialMapper.dtoToEntity(productMaterialDTO);

        productMaterial.setProductSku(
                        productRepository.findById(productSku)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "product with sku=%s not found", productSku)
                        )
        );
        productMaterial.setMaterialSku(
                        materialRepository.findById(materialSku)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "material with sku=%s not found", materialSku)
                        )
        );
        productMaterial.setId(new ProductMaterialId(productSku, materialSku));

        return productMaterial;
    }
}
