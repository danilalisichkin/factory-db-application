package com.factory.backend.core.dto.client;

public class ClientAddingDTO extends ClientDTO {
    public ClientAddingDTO(String phoneNumber, String organizationName, String email, String legalAddress) {
        super(phoneNumber, organizationName, email, legalAddress);
    }
}