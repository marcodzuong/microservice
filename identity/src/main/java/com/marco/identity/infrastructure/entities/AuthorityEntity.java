package com.marco.identity.infrastructure.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "authority")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityEntity implements Serializable {


    @NotNull
    @Size(max = 50)
    @Id
    @Column(length = 50)
    private String name;


}
