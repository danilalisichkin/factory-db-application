package com.factory.backend.controllers.api.postgre;

import com.factory.backend.controllers.abstracts.AbstractProductMaterialController;
import com.factory.backend.services.IProductMaterialService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postgres/api/v1/product-materials")
@Tag(name="ProductMaterialController", description="Provides CRUD-operations with records in \"product_materials\" table")
public class ProductMaterialApiController extends AbstractProductMaterialController {
    @Autowired
    public ProductMaterialApiController(@Qualifier("productMaterialService") IProductMaterialService productMaterialService) {
        super(productMaterialService);
    }
}
