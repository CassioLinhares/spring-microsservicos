package br.com.fiap.entregas.dto;

import br.com.fiap.entregas.model.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EntregaDto {

    private Long numeroEntrega;
    private Long numeroPedido;
    private String nomeEntregador;
    private StatusEntrega statusEntrega;
    private LocalDate dataEntrega;


}
