package br.com.edtech.model.user;

public record DataGetUserAndToken(
        Long id,
        String name,
        String email,
        Boolean ativo,
        String tokenAccess
) {
    public DataGetUserAndToken(User user, String tokenAccess) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAtivo(),
                tokenAccess
        );
    }
}
