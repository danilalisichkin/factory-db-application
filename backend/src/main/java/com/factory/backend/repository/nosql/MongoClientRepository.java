package com.factory.backend.repository.nosql;

import com.factory.backend.entities.nosql.MongoClient;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoClientRepository extends MongoRepository<MongoClient, ObjectId> {
    @Query("{ 'phone_number': ?0 }")
    Optional<MongoClient> findById(String id);

    @Query("{ 'phone_number': ?0 }")
    boolean existsById(String id);
}
