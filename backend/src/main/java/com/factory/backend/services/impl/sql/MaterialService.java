package com.factory.backend.services.impl.sql;

import com.factory.backend.core.dto.material.MaterialAddingDTO;
import com.factory.backend.core.dto.material.MaterialDTO;
import com.factory.backend.core.mappers.material.MaterialMapper;
import com.factory.backend.exceptions.ResourceNotFoundException;
import com.factory.backend.repository.sql.MaterialRepository;
import com.factory.backend.services.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("materialService")
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
                        () -> new ResourceNotFoundException("material with sku=%s not found", id)
                )
        );
    }

    @Override
    public MaterialDTO saveMaterial(MaterialAddingDTO materialDTO) {
        return materialMapper.entityToDto(
                materialRepository.save(materialMapper.addingDtoToEntity(materialDTO))
        );
    }

    @Override
    public MaterialDTO updateMaterial(MaterialDTO materialDTO) {
        if (!materialRepository.existsById(materialDTO.getId())) {
            throw new ResourceNotFoundException("material with sku=%s not found", materialDTO.getId());
        }

        return materialMapper.entityToDto(
                materialRepository.save(materialMapper.dtoToEntity(materialDTO))
        );
    }

    @Override
    public void deleteMaterialById(Integer id) {
        if (!materialRepository.existsById(id)) {
            throw new ResourceNotFoundException("material with sku=%s not found", id);
        }
        materialRepository.deleteById(id);
    }

    @Override
    public void deleteAllMaterials() {
        if (materialRepository.count() == 0) {
            throw new ResourceNotFoundException("no materials found");
        } else {
            materialRepository.deleteAll();
        }
    }
}
