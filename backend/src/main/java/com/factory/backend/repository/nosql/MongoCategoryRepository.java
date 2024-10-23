package com.factory.backend.repository.nosql;

import com.factory.backend.entities.nosql.MongoCategory;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoCategoryRepository extends MongoRepository<MongoCategory, ObjectId> {
    Optional<MongoCategory> findById(Integer id);

    boolean existsById(Integer id);
}
