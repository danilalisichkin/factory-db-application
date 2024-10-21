package com.factory.backend.controllers.api.mongo;

import com.factory.backend.controllers.abstracts.AbstractProductOrderController;
import com.factory.backend.services.IProductOrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mongo/api/v1/product-orders")
@Tag(name="MongoProductOrderController", description="Provides CRUD-operations with records in \"product_orders\" table")
public class MongoProductOrderApiController extends AbstractProductOrderController {
    @Autowired
    public MongoProductOrderApiController(@Qualifier("mongoProductOrderService") IProductOrderService productOrderService) {
        super(productOrderService);
    }
}
