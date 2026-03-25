package br.com.edtech.model.registroConclusao;

import br.com.edtech.model.treinamento.Treinamento;
import br.com.edtech.model.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DataGetConclusao(
        User user,
        Long id,
        LocalDateTime dataConclusao,
        BigDecimal avaliacao,
        Treinamento treinamento
) {
    public DataGetConclusao(RegistroConclusao registroConclusao){
        this(
                registroConclusao.getUser(),
                registroConclusao.getId(),
                registroConclusao.getDataConclusao(),
                registroConclusao.getNotaAvaliacao(),
                registroConclusao.getTreinamento()
        );
    }
}
