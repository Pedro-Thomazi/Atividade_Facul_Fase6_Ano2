package br.com.edtech.model.treinamento;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "treinamento")
@Getter
@Setter
public class Treinamento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "treinamento_seq")
    @SequenceGenerator(name = "treinamento_seq", sequenceName = "SEQ_TREINAMENTO", allocationSize = 1)
    private Long id;
    private String titulo;
    private String descricao;

    public Treinamento() {}

    public Treinamento(DataCreateTreinamento data) {
        this.titulo = data.titulo();
        this.descricao = data.descricao();
    }
}
