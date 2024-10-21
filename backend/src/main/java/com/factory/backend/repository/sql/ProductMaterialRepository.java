package com.factory.backend.repository.sql;

import com.factory.backend.entities.sql.ProductMaterial;
import com.factory.backend.entities.sql.ProductMaterialId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMaterialRepository extends JpaRepository<ProductMaterial, ProductMaterialId> {
}
