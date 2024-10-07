package com.factory.backend.services;

import com.factory.backend.core.dto.material.MaterialAddingDTO;
import com.factory.backend.core.dto.material.MaterialDTO;

import java.util.List;

public interface IMaterialService {
    List<MaterialDTO> getAllMaterials();

    MaterialDTO getMaterialById(Integer id);

    MaterialDTO saveMaterial(MaterialAddingDTO materialDTO);

    MaterialDTO updateMaterial(MaterialDTO materialDTO);

    void deleteMaterialById(Integer id);

    void deleteAllMaterials();
}
