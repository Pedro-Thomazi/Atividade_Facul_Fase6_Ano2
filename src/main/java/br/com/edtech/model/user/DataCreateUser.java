package br.com.edtech.model.user;

public record DataCreateUser(
        String name,
        String password,
        String confirmPassword,
        String email
) {
}
