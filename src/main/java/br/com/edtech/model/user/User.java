package br.com.edtech.model.user;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "SEQ_USERS", allocationSize = 1)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Boolean ativo;

    public  User() {
    }
    public User(DataCreateUser data, String passwordScriptografada) {
        this.name = data.name();
        this.email = data.email();
        this.ativo = true;
        this.password = passwordScriptografada;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void desativeUser() {
        this.ativo = false;
    }

    public User updateUser(@Valid DataUpdateUser newUser) {
        if (newUser.name() != null) {
            this.name = newUser.name();
        }
        return this;
    }
}
