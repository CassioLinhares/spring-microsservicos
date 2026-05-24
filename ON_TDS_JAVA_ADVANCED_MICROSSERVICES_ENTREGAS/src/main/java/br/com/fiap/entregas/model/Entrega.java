package br.com.fiap.entregas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_entregas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_entrega")
    private Long numeroEntrega;

    @Column(name = "numero_pedido")
    @NotNull
    @Positive
    private Long numeroPedido;

    @Column(name = "nome_entregador")
    private String nomeEntregador;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_entrega")
    private StatusEntrega statusEntrega;

    @Column(name = "data_entrega")
    private LocalDate dataEntrega;

}
