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
public class ProductMaterialId implements java.io.Serializable {
    private static final long serialVersionUID = 6606590682131361204L;
    @Column(name = "product_sku", nullable = false)
    private Integer productSku;

    @Column(name = "material_sku", nullable = false)
    private Integer materialSku;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductMaterialId entity = (ProductMaterialId) o;
        return Objects.equals(this.productSku, entity.productSku) &&
                Objects.equals(this.materialSku, entity.materialSku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productSku, materialSku);
    }

}