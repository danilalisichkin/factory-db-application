package com.factory.backend.services.impl.nosql;

import com.factory.backend.core.dto.category.CategoryAddingDTO;
import com.factory.backend.core.dto.category.CategoryDTO;
import com.factory.backend.core.mappers.category.MongoCategoryMapper;
import com.factory.backend.entities.nosql.MongoCategory;
import com.factory.backend.exceptions.ResourceNotFoundException;
import com.factory.backend.repository.nosql.MongoCategoryRepository;
import com.factory.backend.services.ICategoryService;
import com.factory.backend.services.IIdentifierGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("mongoCategoryService")
public class MongoCategoryService implements ICategoryService {

    private final MongoCategoryRepository categoryRepository;

    private final MongoCategoryMapper categoryMapper;

    private final IIdentifierGenerationService identifierGenerationService;

    @Autowired
    public MongoCategoryService(MongoCategoryRepository categoryRepository, MongoCategoryMapper categoryMapper, IIdentifierGenerationService identifierGenerationService) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.identifierGenerationService = identifierGenerationService;
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
        if (categoryDTO.getParentId() != null && !categoryRepository.existsById(categoryDTO.getParentId())) {
            throw new ResourceNotFoundException("parent category with id=%s not found", categoryDTO.getParentId());
        }

        MongoCategory category = categoryMapper.addingDtoToEntity(categoryDTO);
        category.setId(identifierGenerationService.generateCategoryIdentifier());

        return categoryMapper.entityToDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        if (!categoryRepository.existsById(categoryDTO.getId())) {
            throw new ResourceNotFoundException("category with id=%s not found", categoryDTO.getId());
        }

        MongoCategory category = categoryMapper.dtoToEntity(categoryDTO);
        if (categoryDTO.getParentId() != null && !categoryRepository.existsById(category.getId())) {
            throw new ResourceNotFoundException("parent category with id=%s not found", category.getId());
        }

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
