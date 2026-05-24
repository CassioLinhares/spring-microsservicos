package br.com.fiap.entregas.repository;

import br.com.fiap.entregas.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {
        Optional<Entrega> findByNumeroPedido(Long id);
}
