package com.factory.backend.repository.nosql;

import com.factory.backend.entities.nosql.MongoMaterial;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoMaterialRepository extends MongoRepository<MongoMaterial, Integer> {
}
