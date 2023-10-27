package com.marco.catalog.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "catalog_brand")
public class CatalogBrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Brand;

}
