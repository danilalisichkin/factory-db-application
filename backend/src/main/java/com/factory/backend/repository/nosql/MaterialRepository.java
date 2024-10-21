package com.factory.backend.repository.nosql;

import com.factory.backend.entities.nosql.Material;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MaterialRepository extends MongoRepository<Material, Integer> {
}
