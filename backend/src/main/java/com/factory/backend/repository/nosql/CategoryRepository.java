package com.factory.backend.repository.nosql;

import com.factory.backend.entities.nosql.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, Integer> {
}
