package com.factory.backend.controllers.api.converter;

import com.factory.backend.services.impl.converter.ConverterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/converter")
@Tag(name = "ConverterService", description = "Provides convert mechanism for data from PostgreSQL")
public class ConverterController {
    private final ConverterService converterService;

    @Autowired
    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @GetMapping("/convert")
    public ResponseEntity<Void> convert() {
        converterService.ConvertPostgreToMongo();

        return ResponseEntity.ok().build();
    }
}
