package com.factory.backend.controllers.api;

import com.factory.backend.core.dto.category.CategoryAddingDTO;
import com.factory.backend.core.dto.category.CategoryDTO;
import com.factory.backend.services.ICategoryService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryApiController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ICategoryService categoryService;

    @Autowired
    public CategoryApiController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        logger.info("Sending all categories");

        List<CategoryDTO> categories = categoryService.getAllCategories();

        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer id) {
        logger.info("Sending category with id={}", id);

        CategoryDTO categoryDTO = categoryService.getCategoryById(id);

        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryAddingDTO categoryAddingDTO) {
        logger.info("Saving category with name={} and parentId={}", categoryAddingDTO.getName(), categoryAddingDTO.getParentId());

        CategoryDTO savedCategoryDTO = categoryService.saveBook(categoryAddingDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoryDTO);
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO) {
        logger.info("Updating category with id={}", categoryDTO.getId());

        CategoryDTO updatedCategoryDTO = categoryService.updateBook(categoryDTO);

        return ResponseEntity.status(HttpStatus.OK).body(updatedCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        logger.info("Deleting category with id={}", id);

        categoryService.deleteCategoryById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllCategories() {
        logger.info("Deleting all categories");

        categoryService.deleteAllCategories();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}