package com.factory.backend.repository;

import com.factory.backend.entities.ProductMaterial;
import com.factory.backend.entities.ProductMaterialId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMaterialRepository extends JpaRepository<ProductMaterial, ProductMaterialId> {
}
