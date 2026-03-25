package br.com.edtech.service;

import br.com.edtech.exception.BusinessRuleException;
import br.com.edtech.model.user.DataCreateUser;
import br.com.edtech.model.user.DataUpdateUser;
import br.com.edtech.model.user.User;
import br.com.edtech.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServise {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncode;

    public UserServise(UserRepository userRepository, PasswordEncoder passwordEncode) {
        this.userRepository = userRepository;
        this.passwordEncode = passwordEncode;
    }

    @Transactional
    public User create(@Valid DataCreateUser data) {
//        Validacoes
        if (data.name() == null || data.name().isEmpty()) {
            throw new BusinessRuleException("Nome é obrigatorio.");
        }
        if (data.email() == null  || data.email().isEmpty()) {
            throw new BusinessRuleException("E-mail é obrigatorio.");
        }
        if (data.password() == null  || data.password().isEmpty()) {
            throw new BusinessRuleException("Senha é obrigatoria.");
        }
        if (data.confirmPassword() == null || data.confirmPassword().isEmpty()) {
            throw new BusinessRuleException("Confirmação de Senha é obrigatoria.");
        }
        if (!data.password().equals(data.confirmPassword())) {
            throw new BusinessRuleException("Senhas diferentes.");
        }

        var passwordCriptografada = passwordEncode.encode(data.password());
        var user = new User(data, passwordCriptografada);

        return userRepository.save(user);
    }

    public User getMyDetails(User data) {
        return userRepository.getReferenceById(data.getId());
    }

    public void desativeteUser(User data) {
        data.desativeUser();
        userRepository.save(data);
    }

    public User updateUser(@Valid DataUpdateUser newUser, User user) {
        user.updateUser(newUser);
        return userRepository.save(user);
    }
}
