package com.marco.order.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "buyers")
public class BuyerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identity_id", nullable = false)
    private Long identityId;

}
