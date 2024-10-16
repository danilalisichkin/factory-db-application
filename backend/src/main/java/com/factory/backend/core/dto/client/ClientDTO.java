package com.factory.backend.core.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
@Schema(description = "Entry to get/update/delete a record in \" table")
public class ClientDTO {
    @NotNull
    @Pattern(regexp = "^375(15|29|33|44)\\d{7}$",
            message = "illegal format of phone number, correct example: 375291234567")
    private String phoneNumber;

    @NotNull
    @Size(min = 3, max = 100, message = "Organization must be a string with length from 3 to 100 characters")
    private String organizationName;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",
            message = "illegal format of email, correct example: email@mail.org , google@gmail.com")
    private String email;

    @NotNull
    @Size(min = 3, max = 100, message = "Address must be a string with length from 3 to 100 characters")
    private String legalAddress;
}
