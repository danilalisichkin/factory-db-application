package com.factory.backend.controllers.abstracts;

import com.factory.backend.core.dto.category.CategoryAddingDTO;
import com.factory.backend.core.dto.category.CategoryDTO;
import com.factory.backend.services.ICategoryService;
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

public abstract class AbstractCategoryController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected ICategoryService categoryService;

    public AbstractCategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all", description = "Allows to get all existing records")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        logger.info("Sending all categories");

        List<CategoryDTO> categories = categoryService.getAllCategories();

        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get", description = "Allows to get existing record by its id")
    public ResponseEntity<CategoryDTO> getCategory(@NotNull @PathVariable Integer id) {
        logger.info("Sending category with id={}", id);

        CategoryDTO categoryDTO = categoryService.getCategoryById(id);

        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }

    @PostMapping
    @Operation(summary = "Add/save", description = "Allows to add/save new record")
    public ResponseEntity<CategoryDTO> saveCategory(@Valid @RequestBody CategoryAddingDTO categoryAddingDTO) {
        logger.info("Saving category with name={} and parentId={}", categoryAddingDTO.getName(), categoryAddingDTO.getParentId());

        CategoryDTO savedCategoryDTO = categoryService.saveCategory(categoryAddingDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoryDTO);
    }

    @PutMapping
    @Operation(summary = "Update", description = "Allows to update existing record. Note, that update of id fields is not allowed")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        logger.info("Updating category with id={}", categoryDTO.getId());

        CategoryDTO updatedCategoryDTO = categoryService.updateCategory(categoryDTO);

        return ResponseEntity.status(HttpStatus.OK).body(updatedCategoryDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete", description = "Allows to delete existing record by its id")
    public ResponseEntity<Void> deleteCategory(@NotNull @PathVariable Integer id) {
        logger.info("Deleting category with id={}", id);

        categoryService.deleteCategoryById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/all")
    @Operation(summary = "Delete all", description = "Allows to delete all existing records")
    public ResponseEntity<Void> deleteAllCategories() {
        logger.info("Deleting all categories");

        categoryService.deleteAllCategories();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
