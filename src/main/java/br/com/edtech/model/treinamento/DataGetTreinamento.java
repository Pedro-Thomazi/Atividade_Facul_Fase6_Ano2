package br.com.edtech.model.treinamento;

public record DataGetTreinamento(
        Long id,
        String titulo,
        String descricao
) {
    public DataGetTreinamento(Treinamento treinamento) {
        this(
                treinamento.getId(),
                treinamento.getTitulo(),
                treinamento.getDescricao()
        );
    }
}
