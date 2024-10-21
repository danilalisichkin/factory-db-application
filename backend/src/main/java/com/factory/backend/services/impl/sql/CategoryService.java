package com.factory.backend.services.impl.sql;

import com.factory.backend.core.dto.category.CategoryAddingDTO;
import com.factory.backend.core.dto.category.CategoryDTO;
import com.factory.backend.core.mappers.category.CategoryMapper;
import com.factory.backend.entities.sql.Category;
import com.factory.backend.exceptions.ResourceNotFoundException;
import com.factory.backend.repository.sql.CategoryRepository;
import com.factory.backend.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Integer id) {
        return categoryMapper.entityToDto(
                categoryRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("category with id=%s not found", id)
                )
        );
    }

    @Override
    public CategoryDTO saveCategory(CategoryAddingDTO categoryDTO) {
        Category category = categoryMapper.addingDtoToEntity(categoryDTO);
        category.setParent(
                categoryDTO.getParentId() != null
                        ? categoryRepository.findById(categoryDTO.getParentId()).orElseThrow(
                                () -> new ResourceNotFoundException("parent category with id=%s not found", categoryDTO.getParentId()))
                        : null
        );

        return categoryMapper.entityToDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        if (!categoryRepository.existsById(categoryDTO.getId())) {
            throw new ResourceNotFoundException("category with id=%s not found", categoryDTO.getId());
        }

        Category category = categoryMapper.dtoToEntity(categoryDTO);
        category.setParent(
                categoryDTO.getParentId() != null
                        ? categoryRepository.findById(categoryDTO.getParentId()).orElseThrow(
                        () -> new ResourceNotFoundException("parent category with id=%s not found", categoryDTO.getParentId()))
                        : null
        );

        return categoryMapper.entityToDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategoryById(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("category with id=%s not found", id);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteAllCategories() {
        if (categoryRepository.count() == 0) {
            throw new ResourceNotFoundException("no categories found");
        } else {
            categoryRepository.deleteAll();
        }
    }
}
