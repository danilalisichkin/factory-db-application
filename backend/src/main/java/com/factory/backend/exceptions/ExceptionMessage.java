package com.factory.backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ExceptionMessage {
    private String message;
    private String cause;
}
