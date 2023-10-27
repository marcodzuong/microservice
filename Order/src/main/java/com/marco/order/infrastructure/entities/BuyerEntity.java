package com.marco.order.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "buyers")
public class BuyerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "identity_id", nullable = false)
    private Long identityId;

}
