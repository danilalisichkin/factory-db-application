package com.factory.backend.controllers.api.mongo;

import com.factory.backend.controllers.abstracts.AbstractMaterialController;
import com.factory.backend.services.IMaterialService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mongo/api/v1/materials")
@Tag(name="MongoMaterialController", description="Provides CRUD-operations with records in \"materials\" table")
public class MongoMaterialApiController extends AbstractMaterialController {
    @Autowired
    public MongoMaterialApiController(@Qualifier("mongoMaterialService") IMaterialService materialService) {
        super(materialService);
    }
}
