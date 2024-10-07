package com.factory.backend.services.impl;

import com.factory.backend.core.dto.category.CategoryAddingDTO;
import com.factory.backend.core.dto.category.CategoryDTO;
import com.factory.backend.services.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Override
    public List<CategoryDTO> getAllCategories() {
        return List.of();
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        return null;
    }

    @Override
    public CategoryDTO saveBook(CategoryAddingDTO categoryDTO) {
        return null;
    }

    @Override
    public CategoryDTO updateBook(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public void deleteCategoryById(Long id) {

    }

    @Override
    public void deleteAllCategories() {

    }
}
