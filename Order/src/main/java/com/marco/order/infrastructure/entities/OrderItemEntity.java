package com.marco.order.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "discount")
    private Long discount;

    @Column(name = "order_id")
    private Long orderId; //FK

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "unit_price")
    private Long unitPrice;

    @Column(name = "units")
    private int units;



}
