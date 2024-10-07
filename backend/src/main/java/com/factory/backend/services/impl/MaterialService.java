package com.factory.backend.services.impl;

import com.factory.backend.core.dto.material.MaterialAddingDTO;
import com.factory.backend.core.dto.material.MaterialDTO;
import com.factory.backend.core.mappers.material.MaterialMapper;
import com.factory.backend.entities.Material;
import com.factory.backend.exceptions.ResourceNotFoundException;
import com.factory.backend.repository.MaterialRepository;
import com.factory.backend.services.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialService implements IMaterialService {

    private final MaterialRepository materialRepository;

    private final MaterialMapper materialMapper;

    @Autowired
    public MaterialService(MaterialRepository materialRepository, MaterialMapper materialMapper) {
        this.materialRepository = materialRepository;
        this.materialMapper = materialMapper;
    }

    @Override
    public List<MaterialDTO> getAllMaterials() {
        return materialRepository.findAll().stream()
                .map(materialMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MaterialDTO getMaterialById(Integer id) {
        return materialMapper.entityToDto(
                materialRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("Material with id=%s not found", id)
                )
        );
    }

    @Override
    public MaterialDTO saveMaterial(MaterialAddingDTO materialDTO) {
        Material material = materialRepository.save(materialMapper.addingDtoToEntity(materialDTO));

        return materialMapper.entityToDto(material);
    }

    @Override
    public MaterialDTO updateMaterial(MaterialDTO materialDTO) {
        if (materialRepository.findById(materialDTO.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Material with id=%s not found", materialDTO.getId());
        }

        return materialMapper.entityToDto(
                materialRepository.save(materialMapper.dtoToEntity(materialDTO))
        );
    }

    @Override
    public void deleteMaterialById(Integer id) {
        if (materialRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Material with id=%s not found", id);
        }
        materialRepository.deleteById(id);
    }

    @Override
    public void deleteAllMaterials() {
        if (materialRepository.count() == 0) {
            throw new ResourceNotFoundException("No materials found");
        } else {
            materialRepository.deleteAll();
        }
    }
}
