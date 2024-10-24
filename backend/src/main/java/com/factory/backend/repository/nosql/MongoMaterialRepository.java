package com.factory.backend.repository.nosql;

import com.factory.backend.entities.nosql.MongoMaterial;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoMaterialRepository extends MongoRepository<MongoMaterial, ObjectId> {
    Optional<MongoMaterial> findByModelId(Integer id);

    boolean existsByModelId(Integer id);

    void deleteByModelId(Integer id);
}
