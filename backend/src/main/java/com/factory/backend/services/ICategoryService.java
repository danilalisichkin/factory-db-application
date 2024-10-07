package com.factory.backend.services;

import com.factory.backend.core.dto.category.CategoryAddingDTO;
import com.factory.backend.core.dto.category.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Long id);

    CategoryDTO saveBook(CategoryAddingDTO categoryDTO);

    CategoryDTO updateBook(CategoryDTO categoryDTO);

    void deleteCategoryById(Long id);

    void deleteAllCategories();
}
