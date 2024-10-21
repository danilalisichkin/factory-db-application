package com.factory.backend.repository.nosql;

import com.factory.backend.entities.nosql.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, Integer> {
}
