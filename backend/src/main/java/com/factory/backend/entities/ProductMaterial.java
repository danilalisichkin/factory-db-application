package com.factory.backend.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "product_materials")
public class ProductMaterial {
    @SequenceGenerator(name = "product_materials_id_gen", sequenceName = "materials_sku_seq", allocationSize = 1)
    @EmbeddedId
    private ProductMaterialId id;

    @MapsId("productSku")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_sku", nullable = false)
    private Product productSku;

    @MapsId("materialSku")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "material_sku", nullable = false)
    private Material materialSku;

}