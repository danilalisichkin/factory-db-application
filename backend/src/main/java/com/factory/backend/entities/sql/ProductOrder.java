package com.factory.backend.entities.sql;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_orders")
public class ProductOrder {
    @SequenceGenerator(name = "product_orders_id_gen", sequenceName = "products_sku_seq", allocationSize = 1)
    @EmbeddedId
    private ProductOrderId id;

    @MapsId("clientPhoneNumber")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "client_phone_number", nullable = false)
    private Client clientPhoneNumber;

    @MapsId("productSku")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_sku", nullable = false)
    private Product productSku;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

}