package com.factory.backend.entities.sql;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "materials")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "materials_id_gen")
    @SequenceGenerator(name = "materials_id_gen", sequenceName = "materials_sku_seq", allocationSize = 1)
    @Column(name = "sku", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "supplier_name", length = 100)
    private String supplierName;

}