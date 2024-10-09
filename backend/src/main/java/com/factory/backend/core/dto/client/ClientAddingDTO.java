package com.factory.backend.core.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entry to add a new record to \"clients\" table")
public class ClientAddingDTO extends ClientDTO {
    public ClientAddingDTO(String phoneNumber, String organizationName, String email, String legalAddress) {
        super(phoneNumber, organizationName, email, legalAddress);
    }
}