package com.marco.identity.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Entity
@Table(name = "user_authority")
@Getter
public class UserAuthority {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Size(max = 50)
    @Column(name = "authority_name", length = 50, nullable = false)
    private String authorityName;

}

