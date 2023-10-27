package com.marco.order.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "unit_price")
    private Long unitPrice;


    @Column(name = "units")
    private int units;

    @Column(name = "picture_url")
    private String pictureUrl;


}
