package com.factory.backend.controllers.api.postgre;

import com.factory.backend.controllers.abstracts.AbstractMaterialController;
import com.factory.backend.services.IMaterialService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postgres/api/v1/materials")
@Tag(name = "MaterialController", description = "Provides CRUD-operations with records in \"materials\" table")
public class MaterialApiController extends AbstractMaterialController {
    @Autowired
    public MaterialApiController(@Qualifier("materialService")  IMaterialService materialService) {
        super(materialService);
    }
}
