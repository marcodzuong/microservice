package com.marco.identity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class LoginDto {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    private Set<String> authorities;
}
