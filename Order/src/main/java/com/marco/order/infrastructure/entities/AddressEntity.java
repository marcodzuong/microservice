package com.marco.order.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "city", length = 200, nullable = false)
    private String city ;

    @Column(name = "country", length = 200, nullable = false)
    private String country ;

    @Column(name = "state", length = 200, nullable = false)
    private String state ;

    @Column(name = "street", length = 200, nullable = false)
    private String street ;

    @Column(name = "zipCode", length = 200, nullable = false)
    private String zipCode ;

}
