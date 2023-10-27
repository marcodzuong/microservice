package com.marco.catalog.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "catalog_item")
public class CatalogItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Name;
    private String Description;
    private Long Price;
    private String PictureUri;
    private String CatalogTypeId;
    private String CatalogType;

}
