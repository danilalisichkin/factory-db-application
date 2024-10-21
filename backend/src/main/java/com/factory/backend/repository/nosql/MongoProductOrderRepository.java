package com.factory.backend.repository.nosql;

import com.factory.backend.entities.nosql.MongoProductOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoProductOrderRepository extends MongoRepository<MongoProductOrder, String> {
}
