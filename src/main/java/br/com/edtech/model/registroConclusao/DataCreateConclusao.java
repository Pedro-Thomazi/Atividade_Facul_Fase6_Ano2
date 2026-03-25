package br.com.edtech.model.registroConclusao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DataCreateConclusao(
        Long treinamentoId,
        BigDecimal avaliacao
) {
}
