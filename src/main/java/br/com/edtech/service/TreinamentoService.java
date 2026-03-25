package br.com.edtech.service;

import br.com.edtech.exception.BusinessRuleException;
import br.com.edtech.model.treinamento.DataCreateTreinamento;
import br.com.edtech.model.treinamento.Treinamento;
import br.com.edtech.repository.TreinamentoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class TreinamentoService {
    private final TreinamentoRepository treinamentoRepository;

    public TreinamentoService(TreinamentoRepository treinamentoRepository) {
        this.treinamentoRepository = treinamentoRepository;
    }

    public Treinamento create(@Valid DataCreateTreinamento data) {
        if (data.titulo() == null || data.titulo().isEmpty()) {
            throw new BusinessRuleException("Adicione um título.");
        } if (data.descricao() == null || data.descricao().isEmpty()) {
            throw new BusinessRuleException("Adicione uma descrição.");
        }
        var treinamento = new Treinamento(data);
        treinamentoRepository.save(treinamento);
        return treinamento;
    }
}
