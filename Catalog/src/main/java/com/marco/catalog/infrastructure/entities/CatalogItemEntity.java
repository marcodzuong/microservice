package com.marco.catalog.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "catalog_item")
public class CatalogItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 200, nullable = false)
    private String name;
    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long Price;

    @Column(name = "picture_uri")
    private String pictureUri;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_brand_id")
    private CatalogBrandEntity catalogBrand;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_type_id")
    private CatalogTypeEntity catalogType;

}
