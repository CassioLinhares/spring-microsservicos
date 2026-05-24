package br.com.fiap.entregas.dto;

import br.com.fiap.entregas.model.Entrega;
import br.com.fiap.entregas.model.StatusEntrega;

import java.time.LocalDate;

public record EntregaExibicaoDto(
        Long numeroEntrega,
        Long numeroPedido,
        String nomeEntregador,
        StatusEntrega statusEntrega,
        LocalDate dataEntrega
) {
    public EntregaExibicaoDto(Entrega entrega) {
        this(
                entrega.getNumeroEntrega(),
                entrega.getNumeroPedido(),
                entrega.getNomeEntregador(),
                entrega.getStatusEntrega(),
                entrega.getDataEntrega()
        );
    }
}
