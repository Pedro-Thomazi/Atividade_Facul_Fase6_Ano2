package br.com.edtech.service;

import br.com.edtech.exception.BusinessRuleException;
import br.com.edtech.model.registroConclusao.DataCreateConclusao;
import br.com.edtech.model.registroConclusao.RegistroConclusao;
import br.com.edtech.model.treinamento.Treinamento;
import br.com.edtech.model.user.User;
import br.com.edtech.repository.RegistroConclusaoRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistroConclusaoService {
    private final RegistroConclusaoRepository repository;

    public RegistroConclusaoService(RegistroConclusaoRepository repository) {
        this.repository = repository;
    }

    public RegistroConclusao registrarConclusao(DataCreateConclusao data, Treinamento treinamento, User user) {
        if (data.avaliacao() == null) {
            throw new BusinessRuleException("Qual é a avaliação do treinamento.");
        }

        var RegistroConclusao = new RegistroConclusao(data, treinamento, user);
        return repository.save(RegistroConclusao);
    }
}
