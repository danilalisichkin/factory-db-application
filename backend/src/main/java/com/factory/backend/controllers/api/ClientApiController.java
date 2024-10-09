package com.factory.backend.controllers.api;

import com.factory.backend.core.dto.client.ClientAddingDTO;
import com.factory.backend.core.dto.client.ClientDTO;
import com.factory.backend.services.IClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@Tag(name="ClientController", description="Provides CRUD-operations with records in \"clients\" table")
public class ClientApiController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final IClientService clientService;

    @Autowired
    public ClientApiController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all", description = "Allows to get all existing records")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        logger.info("Sending all clients");

        List<ClientDTO> clients = clientService.getAllClients();

        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }

    @GetMapping("/{phone}")
    @Operation(summary = "Get", description = "Allows to get existing record by its id")
    public ResponseEntity<ClientDTO> getClient(
            @NotNull
            @Pattern(regexp = "^\\+375(15|29|33|44)\\d{7}$",
                    message = "illegal format of phone number, correct example: +375291234567")
            @PathVariable String phone) {
        logger.info("Sending client with phone={}", phone);

        ClientDTO clientDTO = clientService.getClientByPhone(phone);

        return ResponseEntity.status(HttpStatus.OK).body(clientDTO);
    }

    @PostMapping
    @Operation(summary = "Add/save", description = "Allows to add/save new record")
    public ResponseEntity<ClientDTO> saveClient(@Valid @RequestBody ClientAddingDTO clientAddingDTO) {
        logger.info("Saving client with phone={}", clientAddingDTO.getPhoneNumber());

        ClientDTO savedClientDTO = clientService.saveClient(clientAddingDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedClientDTO);
    }

    @PutMapping
    @Operation(summary = "Update", description = "Allows to update existing record. Note, that update of id fields is not allowed")
    public ResponseEntity<ClientDTO> updateCategory(@Valid @RequestBody ClientDTO clientDTO) {
        logger.info("Updating client with phone={}", clientDTO.getPhoneNumber());

        ClientDTO updatedCategoryDTO = clientService.updateClient(clientDTO);

        return ResponseEntity.status(HttpStatus.OK).body(updatedCategoryDTO);
    }

    @DeleteMapping("/{phone}")
    @Operation(summary = "Delete", description = "Allows to delete existing record by its id")
    public ResponseEntity<Void> deleteClient(
            @NotNull
            @Pattern(regexp = "^\\+375(15|29|33|44)\\d{7}$",
                    message = "illegal format of phone number, correct example: +375291234567")
            @PathVariable String phone) {
        logger.info("Deleting client with phone={}", phone);

        clientService.deleteClientByPhone(phone);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/all")
    @Operation(summary = "Delete all", description = "Allows to delete all existing records")
    public ResponseEntity<Void> deleteAllClients() {
        logger.info("Deleting all clients");

        clientService.deleteAllClients();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
