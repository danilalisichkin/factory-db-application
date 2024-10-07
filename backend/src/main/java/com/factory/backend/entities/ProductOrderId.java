package com.factory.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ProductOrderId implements java.io.Serializable {
    private static final long serialVersionUID = 7845020949023833500L;
    @Column(name = "client_phone_number", nullable = false, length = 20)
    private String clientPhoneNumber;

    @Column(name = "product_sku", nullable = false)
    private Integer productSku;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductOrderId entity = (ProductOrderId) o;
        return Objects.equals(this.productSku, entity.productSku) &&
                Objects.equals(this.clientPhoneNumber, entity.clientPhoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productSku, clientPhoneNumber);
    }

}