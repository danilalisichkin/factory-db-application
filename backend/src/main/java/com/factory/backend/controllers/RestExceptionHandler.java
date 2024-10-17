package com.factory.backend.controllers;

import com.factory.backend.exceptions.BadRequestException;
import com.factory.backend.exceptions.DataUniquenessConflictException;
import com.factory.backend.exceptions.ExceptionMessage;
import com.factory.backend.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({ResourceNotFoundException.class, EntityNotFoundException.class, NoResourceFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Throwable e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionMessage(e.getMessage(), "resource not found"));
    }

    @ExceptionHandler({MissingServletRequestParameterException.class, BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(Throwable e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionMessage(e.getMessage(), "bad request"));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionMessage("can't read request: param or body is in invalid format", "bad request"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleNoValidException(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errorMap.put(fieldName, errorMessage);
        });

        StringBuilder causeBuilder = new StringBuilder("invalid input provided:\n");
        errorMap.forEach((field, message) ->
                causeBuilder.append("- ").append(field).append(": ").append(message).append("\n")
        );

        String cause = causeBuilder.toString().trim();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionMessage(cause, "validation error"));
    }

    @ExceptionHandler({DataIntegrityViolationException.class, DataUniquenessConflictException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(Throwable e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ExceptionMessage("data with the same unique field already exists, please use other value", "data uniqueness conflict"));
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleOtherException(Throwable e) {
        logger.error("internal server error", e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionMessage("if the error persists, please contact developers", "internal server error"));
    }
}
