package com.factory.backend.controllers.api;

import com.factory.backend.core.dto.material.MaterialAddingDTO;
import com.factory.backend.core.dto.material.MaterialDTO;
import com.factory.backend.services.IMaterialService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
@RequestMapping("/api/v1/materials")
public class MaterialApiController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final IMaterialService materialService;

    @Autowired
    public MaterialApiController(IMaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MaterialDTO>> getAllMaterials() {
        logger.info("Sending all materials");

        List<MaterialDTO> materials = materialService.getAllMaterials();

        return ResponseEntity.status(HttpStatus.OK).body(materials);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDTO> getMaterial(@NotNull @PathVariable Integer id) {
        logger.info("Sending material with id={}", id);

        MaterialDTO materialDTO = materialService.getMaterialById(id);

        return ResponseEntity.status(HttpStatus.OK).body(materialDTO);
    }

    @PostMapping
    public ResponseEntity<MaterialDTO> saveMaterial(@Valid @RequestBody MaterialAddingDTO materialAddingDTO) {
        logger.info("Saving material with name={} and supplier={}", materialAddingDTO.getName(), materialAddingDTO.getSupplierName());

        MaterialDTO savedMaterialDTO = materialService.saveMaterial(materialAddingDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedMaterialDTO);
    }

    @PutMapping
    public ResponseEntity<MaterialDTO> updateMaterial(@Valid @RequestBody MaterialDTO materialDTO) {
        logger.info("Updating material with id={}", materialDTO.getId());

        MaterialDTO updatedCategoryDTO = materialService.updateMaterial(materialDTO);

        return ResponseEntity.status(HttpStatus.OK).body(updatedCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@NotNull @PathVariable Integer id) {
        logger.info("Deleting material with id={}", id);

        materialService.deleteMaterialById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllCategories() {
        logger.info("Deleting all materials");

        materialService.deleteAllMaterials();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
