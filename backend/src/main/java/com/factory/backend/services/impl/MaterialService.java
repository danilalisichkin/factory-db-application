package com.factory.backend.services.impl;

import com.factory.backend.core.dto.material.MaterialAddingDTO;
import com.factory.backend.core.dto.material.MaterialDTO;
import com.factory.backend.services.IMaterialService;

import java.util.List;

public class MaterialService implements IMaterialService {
    @Override
    public List<MaterialDTO> getAllMaterials() {
        return List.of();
    }

    @Override
    public MaterialDTO getMaterialById(Long id) {
        return null;
    }

    @Override
    public MaterialDTO saveMaterial(MaterialAddingDTO materialDTO) {
        return null;
    }

    @Override
    public MaterialDTO updateMaterial(MaterialDTO materialDTO) {
        return null;
    }

    @Override
    public void deleteMaterialById(Integer id) {

    }

    @Override
    public void deleteAllMaterials() {

    }
}
