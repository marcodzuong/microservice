package com.marco.identity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordChangeDTO {

    @NotBlank
    private String currentPassword;
    @NotBlank
    private String newPassword;
}
