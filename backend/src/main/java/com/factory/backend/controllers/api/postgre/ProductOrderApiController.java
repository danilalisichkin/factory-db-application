package com.factory.backend.controllers.api.postgre;

import com.factory.backend.controllers.api.abstracts.AbstractProductOrderController;
import com.factory.backend.services.IProductOrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postgres/api/v1/product-orders")
@Tag(name="ProductOrderController", description="Provides CRUD-operations with records in \"product_orders\" table")
public class ProductOrderApiController extends AbstractProductOrderController {
    @Autowired
    public ProductOrderApiController(IProductOrderService productOrderService) {
        super(productOrderService);
    }
}
