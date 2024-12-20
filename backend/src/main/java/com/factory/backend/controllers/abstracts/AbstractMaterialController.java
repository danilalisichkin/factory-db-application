package com.factory.backend.controllers.abstracts;

import com.factory.backend.core.dto.material.MaterialAddingDTO;
import com.factory.backend.core.dto.material.MaterialDTO;
import com.factory.backend.services.IMaterialService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public abstract class AbstractMaterialController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected IMaterialService materialService;

    public AbstractMaterialController(IMaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all", description = "Allows to get all existing records")
    public ResponseEntity<List<MaterialDTO>> getAllMaterials() {
        logger.info("Sending all materials");

        List<MaterialDTO> materials = materialService.getAllMaterials();

        return ResponseEntity.status(HttpStatus.OK).body(materials);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get", description = "Allows to get existing record by its id")
    public ResponseEntity<MaterialDTO> getMaterial(@NotNull @PathVariable Integer id) {
        logger.info("Sending material with id={}", id);

        MaterialDTO materialDTO = materialService.getMaterialById(id);

        return ResponseEntity.status(HttpStatus.OK).body(materialDTO);
    }

    @PostMapping
    @Operation(summary = "Add/save", description = "Allows to add/save new record")
    public ResponseEntity<MaterialDTO> saveMaterial(@Valid @RequestBody MaterialAddingDTO materialAddingDTO) {
        logger.info("Saving material with name={} and supplier={}", materialAddingDTO.getName(), materialAddingDTO.getSupplierName());

        MaterialDTO savedMaterialDTO = materialService.saveMaterial(materialAddingDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedMaterialDTO);
    }

    @PutMapping
    @Operation(summary = "Update", description = "Allows to update existing record. Note, that update of id fields is not allowed")
    public ResponseEntity<MaterialDTO> updateMaterial(@Valid @RequestBody MaterialDTO materialDTO) {
        logger.info("Updating material with id={}", materialDTO.getId());

        MaterialDTO updatedCategoryDTO = materialService.updateMaterial(materialDTO);

        return ResponseEntity.status(HttpStatus.OK).body(updatedCategoryDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete", description = "Allows to delete existing record by its id")
    public ResponseEntity<Void> deleteMaterial(@NotNull @PathVariable Integer id) {
        logger.info("Deleting material with id={}", id);

        materialService.deleteMaterialById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/all")
    @Operation(summary = "Delete all", description = "Allows to delete all existing records")
    public ResponseEntity<Void> deleteAllCategories() {
        logger.info("Deleting all materials");

        materialService.deleteAllMaterials();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

