package com.factory.backend.controllers.api.mongo;

import com.factory.backend.controllers.api.abstracts.AbstractClientController;
import com.factory.backend.services.IClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mongo/api/mongo/v1/clients")
@Tag(name = "MongoClientController", description = "Provides CRUD-operations with records in \"clients\" table")
public class MongoClientApiController extends AbstractClientController {
    @Autowired
    public MongoClientApiController(IClientService clientService) {
        super(clientService);
    }
}
