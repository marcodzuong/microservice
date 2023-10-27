package com.marco.order.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "card_type")
public class CardTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

}
