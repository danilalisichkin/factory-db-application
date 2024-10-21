package com.factory.backend.controllers.api.postgre;

import com.factory.backend.controllers.api.abstracts.AbstractProductController;
import com.factory.backend.services.IProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/postgres/v1/products")
@Tag(name = "ProductController", description = "Provides CRUD-operations with records in \"products\" table")
public class ProductApiController extends AbstractProductController {
    @Autowired
    public ProductApiController(IProductService productService) {
        super(productService);
    }
}
