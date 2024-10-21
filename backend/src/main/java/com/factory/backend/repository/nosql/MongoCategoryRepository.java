package com.factory.backend.repository.nosql;

import com.factory.backend.entities.nosql.MongoCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoCategoryRepository extends MongoRepository<MongoCategory, Integer> {
}
