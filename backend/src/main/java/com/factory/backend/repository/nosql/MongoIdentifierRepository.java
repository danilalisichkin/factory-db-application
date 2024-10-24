package com.factory.backend.repository.nosql;

import com.factory.backend.entities.nosql.Identifier;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoIdentifierRepository extends MongoRepository<Identifier, ObjectId> {
}
