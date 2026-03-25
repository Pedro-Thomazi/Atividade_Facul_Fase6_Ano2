package br.com.edtech.model.user;

public record DataGetUser(
        Long id,
        String name,
        String email,
        Boolean ativo
) {
    public DataGetUser(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAtivo()
        );
    }
}
