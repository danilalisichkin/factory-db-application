package com.factory.backend.core.dto.client;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class ClientDTO {
    private String phoneNumber;
    private String organizationName;
    private String email;
    private String legalAddress;
}
