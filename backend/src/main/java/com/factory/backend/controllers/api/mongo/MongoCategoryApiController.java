package com.factory.backend.controllers.api.mongo;

import com.factory.backend.controllers.abstracts.AbstractCategoryController;
import com.factory.backend.services.ICategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mongo/api/v1/categories")
@Tag(name = "MongoCategoryController", description = "Provides CRUD-operations with records in \"categories\" table")
public class MongoCategoryApiController extends AbstractCategoryController {
    @Autowired
    public MongoCategoryApiController(@Qualifier("mongoCategoryService") ICategoryService categoryService) {
        super(categoryService);
    }
}
