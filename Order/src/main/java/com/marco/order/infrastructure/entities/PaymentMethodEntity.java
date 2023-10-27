package com.marco.order.infrastructure.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "payment_methods")
public class PaymentMethodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "buyer_id")
    private Long buyerId;

    @Column(name = "card_holder_name")
    private String cardHolderName;

    @Column(name = "alias")
    private String alias;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expiration")
    private Date expiration;

    @OneToOne
    @JoinColumn(name = "card_type_id")
    private CardTypeEntity cardType;

}
