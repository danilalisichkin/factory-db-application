package com.factory.backend.controllers.api.mongo;

import com.factory.backend.controllers.api.abstracts.AbstractProductMaterialController;
import com.factory.backend.services.IProductMaterialService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mongo/api/v1/product-materials")
@Tag(name="MongoProductMaterialController", description="Provides CRUD-operations with records in \"product_materials\" table")
public class MongoProductMaterialApiController extends AbstractProductMaterialController {
    @Autowired
    public MongoProductMaterialApiController(IProductMaterialService productMaterialService) {
        super(productMaterialService);
    }
}
