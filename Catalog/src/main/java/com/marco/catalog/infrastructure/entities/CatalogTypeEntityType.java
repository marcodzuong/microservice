package com.marco.catalog.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "catalog_type")
public class CatalogTypeEntityType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String  Type;
}
