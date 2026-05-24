package br.com.fiap.entregas.service;

import br.com.fiap.entregas.dto.EntregaDto;
import br.com.fiap.entregas.dto.EntregaExibicaoDto;
import br.com.fiap.entregas.exception.EntregaNaoEncotradaException;
import br.com.fiap.entregas.model.Entrega;
import br.com.fiap.entregas.model.StatusEntrega;
import br.com.fiap.entregas.repository.EntregaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    public EntregaExibicaoDto criar(EntregaDto entregaDto){
        Entrega entrega = new Entrega();
        BeanUtils.copyProperties(entregaDto, entrega);
        Entrega entregaCriada = entregaRepository.save(entrega);

        return new EntregaExibicaoDto(entregaCriada);
    }

    public EntregaExibicaoDto buscarPorNumero(Long numeroEntrega){
        Optional<Entrega> entregaOptional = entregaRepository.findById(numeroEntrega);

        if (entregaOptional.isPresent()){
            return new EntregaExibicaoDto(entregaOptional.get());
        } else {
            throw new EntregaNaoEncotradaException("Entrega não encontrada!");
        }
    }

    public List<EntregaExibicaoDto> listarTodasAsEntregas(){
        return entregaRepository
                .findAll()
                .stream()
                .map(EntregaExibicaoDto::new)
                .toList();
    }

    public void excluir(Long numeroEntrega){
        Optional<Entrega> entregaOptional = entregaRepository.findById(numeroEntrega);

        if (entregaOptional.isPresent()){
            entregaRepository.delete(entregaOptional.get());
        } else {
            throw new RuntimeException("Entrega não encontrada!");
        }
    }

    public EntregaExibicaoDto atualizar(EntregaDto entregaDto){
        Entrega entrega = new Entrega();
        BeanUtils.copyProperties(entregaDto, entrega);
        Entrega entregaAtualizada = entregaRepository.save(entrega);

        return new EntregaExibicaoDto(entregaAtualizada);
    }

    public void colocarEmEntrega(Long id){
        Optional<Entrega> entregaOptional = entregaRepository.findByNumeroPedido(id);

        if (!entregaOptional.isPresent()){
            throw new EntregaNaoEncotradaException("Entrega não encontrada!");
        }

        Entrega entrega = entregaOptional.get();
        entrega.setStatusEntrega(StatusEntrega.EM_TRANSPORTE);
        entregaRepository.save(entrega);

    }


}













