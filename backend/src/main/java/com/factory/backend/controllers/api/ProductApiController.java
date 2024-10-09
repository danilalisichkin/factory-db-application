package com.factory.backend.controllers.api;

import com.factory.backend.core.dto.product.ProductAddingDTO;
import com.factory.backend.core.dto.product.ProductDTO;
import com.factory.backend.services.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductApiController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final IProductService productService;

    @Autowired
    public ProductApiController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        logger.info("Sending all products");

        List<ProductDTO> products = productService.getAllProducts();

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id) {
        logger.info("Sending product with id={}", id);

        ProductDTO productDTO = productService.getProductById(id);

        return ResponseEntity.status(HttpStatus.OK).body(productDTO);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> saveMaterial(@RequestBody ProductAddingDTO productAddingDTO) {
        logger.info("Saving product with name={} and category_id={}", productAddingDTO.getName(), productAddingDTO.getCategoryId());

        ProductDTO savedProductDTO = productService.saveProduct(productAddingDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProductDTO);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProducts(@RequestBody ProductDTO productDTO) {
        logger.info("Updating product with id={}", productDTO.getId());

        ProductDTO updatedProductsDTO = productService.updateProduct(productDTO);

        return ResponseEntity.status(HttpStatus.OK).body(updatedProductsDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        logger.info("Deleting product with id={}", id);

        productService.deleteProductById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllProducts() {
        logger.info("Deleting all products");

        productService.deleteAllProducts();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
