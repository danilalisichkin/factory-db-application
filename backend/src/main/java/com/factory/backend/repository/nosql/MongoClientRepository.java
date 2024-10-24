package com.factory.backend.repository.nosql;

import com.factory.backend.entities.nosql.MongoClient;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoClientRepository extends MongoRepository<MongoClient, ObjectId> {
    Optional<MongoClient> findByPhoneNumber(String id);

    boolean existsByPhoneNumber(String id);

    void deleteByPhoneNumber(String id);
}
