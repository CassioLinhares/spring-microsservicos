package br.com.fiap.pedidos.dto;

import br.com.fiap.pedidos.model.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PedidoDto {

    private Long numeroPedido;
    private String nomeCliente;
    private LocalDate dataPedido;
    private BigDecimal valor;
    private StatusEntrega statusEntrega;

}
