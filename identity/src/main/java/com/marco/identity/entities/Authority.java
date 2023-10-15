package com.marco.identity.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "authority")
@Getter
public class Authority implements Serializable {


    @NotNull
    @Size(max = 50)
    @Id
    @Column(length = 50)
    private String name;


}
