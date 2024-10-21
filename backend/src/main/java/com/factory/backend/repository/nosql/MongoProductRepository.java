package com.factory.backend.repository.nosql;

import com.factory.backend.entities.nosql.MongoProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoProductRepository extends MongoRepository<MongoProduct, Integer> {
}
