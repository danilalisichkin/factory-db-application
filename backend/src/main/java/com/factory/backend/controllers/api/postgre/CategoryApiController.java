package com.factory.backend.controllers.api.postgre;

import com.factory.backend.controllers.api.abstracts.AbstractCategoryController;
import com.factory.backend.services.ICategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/postgres/v1/categories")
@Tag(name = "CategoryController", description = "Provides CRUD-operations with records in \"categories\" table")
public class CategoryApiController extends AbstractCategoryController {
    @Autowired
    public CategoryApiController(ICategoryService categoryService) {
        super(categoryService);
    }
}
