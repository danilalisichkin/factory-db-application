package com.factory.backend.controllers.api.abstracts;

import com.factory.backend.core.dto.product.order.ProductOrderAddingDTO;
import com.factory.backend.core.dto.product.order.ProductOrderDTO;
import com.factory.backend.services.IProductOrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

public abstract class AbstractProductOrderController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected IProductOrderService productOrderService;

    public AbstractProductOrderController(IProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all", description = "Allows to get all existing records")
    public ResponseEntity<List<ProductOrderDTO>> getAllProductOrders() {
        logger.info("Sending all product orders");

        List<ProductOrderDTO> productOrders = productOrderService.getAllProductOrders();

        return ResponseEntity.status(HttpStatus.OK).body(productOrders);
    }

    @GetMapping("/{client_phone}/{product_id}")
    @Operation(summary = "Get", description = "Allows to get existing record by its id")
    public ResponseEntity<ProductOrderDTO> getProductOrder(
            @NotNull
            @Pattern(regexp = "^375(15|29|33|44)\\d{7}$",
                    message = "illegal format of phone number, correct example: 375291234567")
            @PathVariable String client_phone,
            @NotNull @PathVariable Integer product_id) {
        logger.info("Sending product order with client_phone={} and product_id={}", client_phone, product_id);

        ProductOrderDTO productOrderDTO = productOrderService.getProductOrderById(client_phone, product_id);

        return ResponseEntity.status(HttpStatus.OK).body(productOrderDTO);
    }

    @PostMapping
    @Operation(summary = "Add/save", description = "Allows to add/save new record")
    public ResponseEntity<ProductOrderDTO> saveProductOrder(@Valid @RequestBody ProductOrderAddingDTO productOrderAddingDTO) {
        logger.info("Saving product order with client_phone={} and product_id={}", productOrderAddingDTO.getClientPhoneNumber(), productOrderAddingDTO.getProductSku());

        ProductOrderDTO savedProductMaterialDTO = productOrderService.saveProductOrder(productOrderAddingDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProductMaterialDTO);
    }

    @PutMapping
    @Operation(summary = "Update", description = "Allows to update existing record. Note, that update of id fields is not allowed")
    public ResponseEntity<ProductOrderDTO> updateProductOrder(@Valid @RequestBody ProductOrderDTO productOrderDTO) {
        logger.info("Updating product order with client_phone={} and product_id={}", productOrderDTO.getClientPhoneNumber(), productOrderDTO.getProductSku());

        ProductOrderDTO updatedProductOrderDTO = productOrderService.updateProductOrder(productOrderDTO);

        return ResponseEntity.status(HttpStatus.OK).body(updatedProductOrderDTO);
    }

    @DeleteMapping("/{client_phone}/{product_id}")
    @Operation(summary = "Delete", description = "Allows to delete existing record by its id")
    public ResponseEntity<Void> deleteProductOrder(
            @NotNull
            @Pattern(regexp = "^375(15|29|33|44)\\d{7}$",
                    message = "illegal format of phone number, correct example: 375291234567")
            @PathVariable String client_phone,
            @NotNull @PathVariable Integer product_id) {
        logger.info("Deleting product order with client_phone={} amd product_id={}", client_phone, product_id);

        productOrderService.deleteProductOrderById(client_phone, product_id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/all")
    @Operation(summary = "Delete all", description = "Allows to delete all existing records")
    public ResponseEntity<Void> deleteAllProductOrders() {
        logger.info("Deleting all product orders");

        productOrderService.deleteAllProductOrders();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
