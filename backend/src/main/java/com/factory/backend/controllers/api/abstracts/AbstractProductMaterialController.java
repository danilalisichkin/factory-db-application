package com.factory.backend.controllers.api.abstracts;

import com.factory.backend.core.dto.product.material.ProductMaterialAddingDTO;
import com.factory.backend.core.dto.product.material.ProductMaterialDTO;
import com.factory.backend.services.IProductMaterialService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public abstract class AbstractProductMaterialController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected IProductMaterialService productMaterialService;

    public AbstractProductMaterialController(IProductMaterialService productMaterialService) {
        this.productMaterialService = productMaterialService;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all", description = "Allows to get all existing records")
    public ResponseEntity<List<ProductMaterialDTO>> getAllProductMaterials() {
        logger.info("Sending all product materials");

        List<ProductMaterialDTO> productMaterials = productMaterialService.getAllProductMaterials();

        return ResponseEntity.status(HttpStatus.OK).body(productMaterials);
    }

    @GetMapping("/{product_id}/{material_id}")
    @Operation(summary = "Get", description = "Allows to get existing record by its id")
    public ResponseEntity<ProductMaterialDTO> getProductMaterial(
            @NotNull @PathVariable Integer product_id,
            @NotNull @PathVariable Integer material_id) {
        logger.info("Sending product material with product_id={} and material_id={}", product_id, material_id);

        ProductMaterialDTO productMaterialDTO = productMaterialService.getProductMaterialById(product_id, material_id);

        return ResponseEntity.status(HttpStatus.OK).body(productMaterialDTO);
    }

    @PostMapping
    @Operation(summary = "Add/save", description = "Allows to add/save new record")
    public ResponseEntity<ProductMaterialDTO> saveProductMaterial(@Valid @RequestBody ProductMaterialAddingDTO productMaterialAddingDTO) {
        logger.info("Saving product material with product_id={} and material_id={}", productMaterialAddingDTO.getProductSku(), productMaterialAddingDTO.getMaterialSku());

        ProductMaterialDTO savedProductMaterialDTO = productMaterialService.saveProductMaterial(productMaterialAddingDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProductMaterialDTO);
    }

    @PutMapping
    @Operation(summary = "Update", description = "Allows to update existing record. Note, that update of id fields is not allowed")
    public ResponseEntity<ProductMaterialDTO> updateProductMaterial(@Valid @RequestBody ProductMaterialDTO productMaterialDTO) {
        logger.info("Updating product material with product_id={} and material_id={}", productMaterialDTO.getProductSku(), productMaterialDTO.getMaterialSku());

        ProductMaterialDTO updatedProductMaterialDTO = productMaterialService.updateProductMaterial(productMaterialDTO);

        return ResponseEntity.status(HttpStatus.OK).body(updatedProductMaterialDTO);
    }

    @DeleteMapping("/{product_id}/{material_id}")
    @Operation(summary = "Delete", description = "Allows to delete existing record by its id")
    public ResponseEntity<Void> deleteProductMaterial(
            @NotNull @PathVariable Integer product_id,
            @NotNull @PathVariable Integer material_id) {
        logger.info("Deleting product material with product_id={} amd material_id={}", product_id, material_id);

        productMaterialService.deleteProductMaterialById(product_id, material_id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/all")
    @Operation(summary = "Delete all", description = "Allows to delete all existing records")
    public ResponseEntity<Void> deleteAllProductMaterials() {
        logger.info("Deleting all product materials");

        productMaterialService.deleteAllProductMaterials();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
