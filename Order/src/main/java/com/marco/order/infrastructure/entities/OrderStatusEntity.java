package com.marco.order.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "orders_status")
public class OrderStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

}
