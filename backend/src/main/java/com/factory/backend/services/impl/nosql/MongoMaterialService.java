package com.factory.backend.services.impl.nosql;

import com.factory.backend.core.dto.material.MaterialAddingDTO;
import com.factory.backend.core.dto.material.MaterialDTO;
import com.factory.backend.core.mappers.material.MongoMaterialMapper;
import com.factory.backend.entities.nosql.MongoMaterial;
import com.factory.backend.exceptions.ResourceNotFoundException;
import com.factory.backend.repository.nosql.MongoMaterialRepository;
import com.factory.backend.services.IIdentifierGenerationService;
import com.factory.backend.services.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("mongoMaterialService")
public class MongoMaterialService implements IMaterialService {

    private final MongoMaterialRepository materialRepository;

    private final MongoMaterialMapper materialMapper;

    private final IIdentifierGenerationService identifierGenerationService;

    @Autowired
    public MongoMaterialService(MongoMaterialRepository materialRepository, MongoMaterialMapper materialMapper, IIdentifierGenerationService identifierGenerationService) {
        this.materialRepository = materialRepository;
        this.materialMapper = materialMapper;
        this.identifierGenerationService = identifierGenerationService;
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
        MongoMaterial mongoMaterial = materialMapper.addingDtoToEntity(materialDTO);
        mongoMaterial.setId(identifierGenerationService.generateMaterialIdentifier());

        return materialMapper.entityToDto(materialRepository.save(mongoMaterial));
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
