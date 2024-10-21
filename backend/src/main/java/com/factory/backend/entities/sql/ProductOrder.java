package com.factory.backend.entities.sql;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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