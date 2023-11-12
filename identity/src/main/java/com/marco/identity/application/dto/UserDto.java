package com.marco.identity.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Setter
@Getter
public class UserDto {

    @NotBlank
    private String userName;

    @NotNull
    private Set<String> authorities;
}
