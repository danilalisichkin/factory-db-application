package com.factory.backend.repository.nosql;

import com.factory.backend.entities.nosql.MongoProductMaterial;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoProductMaterialRepository extends MongoRepository<MongoProductMaterial, ObjectId> {
    Optional<MongoProductMaterial> findByModelId(String id);

    boolean existsByModelId(String id);

    void deleteByModelId(String id);
}
