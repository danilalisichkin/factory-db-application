package com.factory.backend.controllers.api;

import com.factory.backend.core.dto.product.order.ProductOrderAddingDTO;
import com.factory.backend.core.dto.product.order.ProductOrderDTO;
import com.factory.backend.services.IProductOrderService;
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
public class ProductOrderApiController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final IProductOrderService productOrderService;

    @Autowired
    public ProductOrderApiController(IProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductOrderDTO>> getAllProductOrders() {
        logger.info("Sending all product orders");

        List<ProductOrderDTO> productOrders = productOrderService.getAllProductOrders();

        return ResponseEntity.status(HttpStatus.OK).body(productOrders);
    }

    @GetMapping("/{client_phone}/{product_id}")
    public ResponseEntity<ProductOrderDTO> getProductOrder(@PathVariable String client_phone, @PathVariable Integer product_id) {
        logger.info("Sending product order with client_phone={} and product_id={}", client_phone, product_id);

        ProductOrderDTO productOrderDTO = productOrderService.getProductOrderById(client_phone, product_id);

        return ResponseEntity.status(HttpStatus.OK).body(productOrderDTO);
    }

    @PostMapping
    public ResponseEntity<ProductOrderDTO> saveProductOrder(@RequestBody ProductOrderAddingDTO productOrderAddingDTO) {
        logger.info("Saving product order with client_phone={} and product_id={}", productOrderAddingDTO.getClientPhoneNumber(), productOrderAddingDTO.getProductSku());

        ProductOrderDTO savedProductMaterialDTO = productOrderService.saveProductOrder(productOrderAddingDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProductMaterialDTO);
    }

    @PutMapping
    public ResponseEntity<ProductOrderDTO> updateProductOrder(@RequestBody ProductOrderDTO productOrderDTO) {
        logger.info("Updating product order with client_phone={} and product_id={}", productOrderDTO.getClientPhoneNumber(), productOrderDTO.getProductSku());

        ProductOrderDTO updatedProductOrderDTO = productOrderService.updateProductOrder(productOrderDTO);

        return ResponseEntity.status(HttpStatus.OK).body(updatedProductOrderDTO);
    }

    @DeleteMapping("/{client_phone}/{product_id}")
    public ResponseEntity<Void> deleteProductOrder(@PathVariable String client_phone, @PathVariable Integer product_id) {
        logger.info("Deleting product order with client_phone={} amd product_id={}", client_phone, product_id);

        productOrderService.deleteProductOrderById(client_phone, product_id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllProductOrders() {
        logger.info("Deleting all product orders");

        productOrderService.deleteAllProductOrders();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
