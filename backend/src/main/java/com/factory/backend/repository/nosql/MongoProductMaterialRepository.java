package com.factory.backend.repository.nosql;

import com.factory.backend.entities.nosql.MongoProductMaterial;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoProductMaterialRepository extends MongoRepository<MongoProductMaterial, String> {
}
