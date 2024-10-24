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