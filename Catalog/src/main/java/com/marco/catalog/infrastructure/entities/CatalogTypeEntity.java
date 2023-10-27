package com.marco.catalog.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "catalog_type")
public class CatalogTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", length = 200, nullable = false)
    private String type;

}
