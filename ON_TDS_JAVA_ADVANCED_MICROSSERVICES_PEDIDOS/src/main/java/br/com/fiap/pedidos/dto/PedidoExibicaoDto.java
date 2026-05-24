package br.com.fiap.pedidos.dto;

import br.com.fiap.pedidos.model.Pedido;
import br.com.fiap.pedidos.model.StatusEntrega;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PedidoExibicaoDto(
        Long numeroPedido,
        String nomeCliente,
        LocalDate dataPedido,
        BigDecimal valor,
        StatusEntrega statusEntrega
) {
    public PedidoExibicaoDto(Pedido pedido){
        this(
                pedido.getNumeroPedido(),
                pedido.getNomeCliente(),
                pedido.getDataPedido(),
                pedido.getValor(),
                pedido.getStatusEntrega()
        );
    }
}
