package com.factory.backend.controllers.api.mongo;

import com.factory.backend.controllers.api.abstracts.AbstractProductController;
import com.factory.backend.services.IProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mongo/api/v1/products")
@Tag(name="MongoProductController", description="Provides CRUD-operations with records in \"products\" table")
public class MongoProductApiController extends AbstractProductController {
    @Autowired
    public MongoProductApiController(@Qualifier("mongoProductService") IProductService productService) {
        super(productService);
    }
}
