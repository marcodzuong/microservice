package com.marco.order.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String City ;
    private String Country ;
    private String State ;
    private String Street ;
    private String ZipCode ;

}
