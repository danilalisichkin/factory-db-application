package com.factory.backend.controllers.api.postgre;

import com.factory.backend.controllers.abstracts.AbstractClientController;
import com.factory.backend.services.IClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postgres/api/v1/clients")
@Tag(name = "ClientController", description = "Provides CRUD-operations with records in \"clients\" table")
public class ClientApiController extends AbstractClientController {
    @Autowired
    public ClientApiController(@Qualifier("clientService") IClientService clientService) {
        super(clientService);
    }
}
