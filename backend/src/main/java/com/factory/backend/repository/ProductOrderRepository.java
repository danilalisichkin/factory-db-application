package com.factory.backend.repository;

import com.factory.backend.entities.ProductOrder;
import com.factory.backend.entities.ProductOrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, ProductOrderId> {
}
