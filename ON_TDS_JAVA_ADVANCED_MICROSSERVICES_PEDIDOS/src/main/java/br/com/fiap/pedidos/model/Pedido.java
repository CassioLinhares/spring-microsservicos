package br.com.fiap.pedidos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_pedido")
    private Long numeroPedido;

    @NotBlank
    @Size(min = 3, max = 100)
    @Column(name = "nome_cliente")
    private String nomeCliente;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status_entrega")
    private StatusEntrega statusEntrega;

}
