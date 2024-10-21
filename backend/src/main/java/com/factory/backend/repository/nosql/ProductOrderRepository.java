package com.factory.backend.repository.nosql;

import com.factory.backend.entities.nosql.ProductOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductOrderRepository extends MongoRepository<ProductOrder, String> {
}
