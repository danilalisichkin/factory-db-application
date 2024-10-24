package com.factory.backend.controllers.abstracts;

import com.factory.backend.core.dto.product.ProductAddingDTO;
import com.factory.backend.core.dto.product.ProductDTO;
import com.factory.backend.services.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class AbstractProductController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected IProductService productService;

    public AbstractProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all", description = "Allows to get all existing records")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        logger.info("Sending all products");

        List<ProductDTO> products = productService.getAllProducts();

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get", description = "Allows to get existing record by its id")
    public ResponseEntity<ProductDTO> getProduct(@NotNull @PathVariable Integer id) {
        logger.info("Sending product with id={}", id);

        ProductDTO productDTO = productService.getProductById(id);

        return ResponseEntity.status(HttpStatus.OK).body(productDTO);
    }

    @PostMapping
    @Operation(summary = "Add/save", description = "Allows to add/save new record")
    public ResponseEntity<ProductDTO> saveMaterial(@Valid @RequestBody ProductAddingDTO productAddingDTO) {
        logger.info("Saving product with name={} and category_id={}", productAddingDTO.getName(), productAddingDTO.getCategoryId());

        ProductDTO savedProductDTO = productService.saveProduct(productAddingDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProductDTO);
    }

    @PutMapping
    @Operation(summary = "Update", description = "Allows to update existing record. Note, that update of id fields is not allowed")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO) {
        logger.info("Updating product with id={}", productDTO.getId());

        ProductDTO updatedProductDTO = productService.updateProduct(productDTO);

        return ResponseEntity.status(HttpStatus.OK).body(updatedProductDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete", description = "Allows to delete existing record by its id")
    public ResponseEntity<Void> deleteProduct(@NotNull @PathVariable Integer id) {
        logger.info("Deleting product with id={}", id);

        productService.deleteProductById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/all")
    @Operation(summary = "Delete all", description = "Allows to delete all existing records")
    public ResponseEntity<Void> deleteAllProducts() {
        logger.info("Deleting all products");

        productService.deleteAllProducts();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

