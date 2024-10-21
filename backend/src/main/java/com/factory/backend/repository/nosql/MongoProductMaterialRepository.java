package com.factory.backend.repository.nosql;

import com.factory.backend.entities.sql.ProductMaterial;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoProductMaterialRepository extends MongoRepository<ProductMaterial, String> {
}
