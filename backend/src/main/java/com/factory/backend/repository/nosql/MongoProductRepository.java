package com.factory.backend.repository.nosql;

import com.factory.backend.entities.nosql.MongoProduct;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoProductRepository extends MongoRepository<MongoProduct, ObjectId> {
    Optional<MongoProduct> findById(Integer id);
}
