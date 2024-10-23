package com.factory.backend.repository.nosql;

import com.factory.backend.entities.nosql.MongoProductOrder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoProductOrderRepository extends MongoRepository<MongoProductOrder, ObjectId> {
    Optional<MongoProductOrder> findById(String id);
}
