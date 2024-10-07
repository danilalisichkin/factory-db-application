package com.factory.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DataUniquenessConflictException extends RuntimeException {
    public DataUniquenessConflictException() {

    }
}
