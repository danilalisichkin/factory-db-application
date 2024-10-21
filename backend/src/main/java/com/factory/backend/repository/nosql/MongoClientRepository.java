package com.factory.backend.repository.nosql;

import com.factory.backend.entities.nosql.MongoClient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoClientRepository extends MongoRepository<MongoClient, String> {
}
