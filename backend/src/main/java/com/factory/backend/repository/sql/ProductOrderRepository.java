package com.factory.backend.repository.sql;

import com.factory.backend.entities.sql.ProductOrder;
import com.factory.backend.entities.sql.ProductOrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, ProductOrderId> {
}
