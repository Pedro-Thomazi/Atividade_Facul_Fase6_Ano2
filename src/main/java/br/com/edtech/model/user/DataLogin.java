package br.com.edtech.model.user;

import jakarta.validation.constraints.NotBlank;

public record DataLogin(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
