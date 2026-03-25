package br.com.edtech.repository;

import br.com.edtech.model.registroConclusao.RegistroConclusao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroConclusaoRepository extends JpaRepository<RegistroConclusao, Long> {
}
