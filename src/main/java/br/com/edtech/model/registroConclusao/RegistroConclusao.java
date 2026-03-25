package br.com.edtech.model.registroConclusao;

import br.com.edtech.model.treinamento.Treinamento;
import br.com.edtech.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "conclusao")
@Getter
@Setter
public class RegistroConclusao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "registro_conclusao_seq")
    @SequenceGenerator(name = "registro_conclusao_seq", sequenceName = "SEQ_REGISTRO_CONCLUSAO", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "treinamento_id", nullable = false)
    private Treinamento treinamento;
    private BigDecimal notaAvaliacao;
    private LocalDateTime dataConclusao;

    public RegistroConclusao() {}

    public RegistroConclusao(DataCreateConclusao data, Treinamento treinamento, User user) {
        this.user = user;
        this.treinamento = treinamento;
        this.notaAvaliacao = data.avaliacao();
        this.dataConclusao = LocalDateTime.now();
    }
}
