package com.factory.backend.services.impl.nosql;

import com.factory.backend.core.dto.product.material.ProductMaterialAddingDTO;
import com.factory.backend.core.dto.product.material.ProductMaterialDTO;
import com.factory.backend.core.mappers.product.material.MongoProductMaterialMapper;
import com.factory.backend.entities.nosql.MongoProductMaterial;
import com.factory.backend.exceptions.ResourceNotFoundException;
import com.factory.backend.repository.nosql.MongoMaterialRepository;
import com.factory.backend.repository.nosql.MongoProductMaterialRepository;
import com.factory.backend.repository.nosql.MongoProductRepository;
import com.factory.backend.services.IProductMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("mongoProductMaterialService")
public class MongoProductMaterialService implements IProductMaterialService {

    private final MongoProductMaterialRepository productMaterialRepository;

    private final MongoProductRepository productRepository;

    private final MongoMaterialRepository materialRepository;

    private final MongoProductMaterialMapper productMaterialMapper;

    @Autowired
    public MongoProductMaterialService(
            MongoProductMaterialRepository productMaterialRepository,
            MongoProductRepository productRepository,
            MongoMaterialRepository materialRepository,
            MongoProductMaterialMapper productMaterialMapper
    ) {
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
                productMaterialRepository.findByModelId(MongoProductMaterial.generateId(productId, materialId)).orElseThrow(
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
        if (!productMaterialRepository.existsByModelId(MongoProductMaterial.generateId(
                productMaterialDTO.getProductSku(),
                productMaterialDTO.getMaterialSku()
        ))) {
            throw new ResourceNotFoundException("product material with product_sku=%s and material_sku=%s not found", productMaterialDTO.getProductSku(), productMaterialDTO.getMaterialSku());
        }

        return productMaterialMapper.entityToDto(productMaterialRepository.save(populateProductMaterial(productMaterialDTO)));
    }

    @Override
    public void deleteProductMaterialById(Integer productId, Integer materialId) {
        if (!productMaterialRepository.existsByModelId(MongoProductMaterial.generateId(productId, materialId))) {
            throw new ResourceNotFoundException("product material with product_sku=%s and material_sku=%s not found", productId, materialId);
        }
        productMaterialRepository.deleteByModelId(MongoProductMaterial.generateId(productId, materialId));
    }

    @Override
    public void deleteAllProductMaterials() {
        if (productMaterialRepository.count() == 0) {
            throw new ResourceNotFoundException("no product materials found");
        } else {
            productMaterialRepository.deleteAll();
        }
    }

    private MongoProductMaterial populateProductMaterial(ProductMaterialDTO productMaterialDTO) {
        final Integer productSku = productMaterialDTO.getProductSku();
        final Integer materialSku = productMaterialDTO.getMaterialSku();

        if (productSku == null)
            throw new ResourceNotFoundException("product sku is required");
        if (materialSku == null)
            throw new ResourceNotFoundException("material sku is required");

        if (!productRepository.existsByModelId(productSku))
            throw new ResourceNotFoundException("product with sku=%s not found", productSku);
        if (!materialRepository.existsByModelId(materialSku))
            throw new ResourceNotFoundException("material with sku=%s not found", materialSku);

        MongoProductMaterial productMaterial = productMaterialMapper.dtoToEntity(productMaterialDTO);
        productMaterial.generateId();

        return productMaterial;
    }
}
