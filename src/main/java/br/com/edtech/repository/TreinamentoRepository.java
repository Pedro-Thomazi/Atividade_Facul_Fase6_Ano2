package br.com.edtech.repository;

import br.com.edtech.model.treinamento.Treinamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinamentoRepository extends JpaRepository<Treinamento, Long> {
}
