package com.factory.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @SequenceGenerator(name = "clients_id_gen", sequenceName = "materials_sku_seq", allocationSize = 1)
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "organization_name", nullable = false)
    private String organizationName;

    @Column(name = "email")
    private String email;

    @Column(name = "legal_address")
    private String legalAddress;

}