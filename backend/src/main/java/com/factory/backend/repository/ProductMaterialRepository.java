package com.factory.backend.repository;

import com.factory.backend.entities.ProductMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMaterialRepository extends JpaRepository<ProductMaterial, Integer> {
}
