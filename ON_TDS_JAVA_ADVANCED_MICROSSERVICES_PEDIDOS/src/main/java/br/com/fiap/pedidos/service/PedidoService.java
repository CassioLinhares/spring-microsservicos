package br.com.fiap.pedidos.service;

import br.com.fiap.pedidos.dto.PedidoDto;
import br.com.fiap.pedidos.dto.PedidoExibicaoDto;
import br.com.fiap.pedidos.exception.PedidoNaoEncontradoException;
import br.com.fiap.pedidos.http.EntregaClient;
import br.com.fiap.pedidos.model.Pedido;
import br.com.fiap.pedidos.model.StatusEntrega;
import br.com.fiap.pedidos.repository.PedidoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EntregaClient entregaClient;

    public void colocarEmTransporte(Long id){
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);

        if(pedidoOptional.isPresent()){
            pedidoOptional.get().setStatusEntrega(StatusEntrega.EM_TRANSPORTE);
            pedidoRepository.save(pedidoOptional.get());
            entregaClient.atualizaEntrega(pedidoOptional.get().getNumeroPedido());
        } else {
            throw new PedidoNaoEncontradoException("Pedido não encontrado");
        }

    }


    public PedidoExibicaoDto criar(PedidoDto pedidoDto){

        Pedido pedido = new Pedido();
        BeanUtils.copyProperties(pedidoDto, pedido);

        pedido.setStatusEntrega(StatusEntrega.EM_SEPARACAO);
        Pedido pedidoCriado = pedidoRepository.save(pedido);

        return new PedidoExibicaoDto(pedidoCriado);

    }

    public PedidoExibicaoDto buscarPorNumeroPedido(Long numeroPedido){
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(numeroPedido);

        if (pedidoOptional.isPresent()){
            return new PedidoExibicaoDto(pedidoOptional.get());
        } else {
            throw new PedidoNaoEncontradoException(String.format("Pedido %s não existe!", numeroPedido));
        }
    }

    public List<PedidoExibicaoDto> exibirTodosOsPedidos(){
        return pedidoRepository
                .findAll()
                .stream()
                .map(PedidoExibicaoDto::new)
                .toList();
    }

    public void excluir(Long numeroPedido){

        Optional<Pedido> pedidoOptional = pedidoRepository.findById(numeroPedido);

        if (pedidoOptional.isPresent()){
            pedidoRepository.delete(pedidoOptional.get());
        } else {
            throw new PedidoNaoEncontradoException(String.format("Pedido %s não existe!", numeroPedido));
        }
        Pedido pedido = new Pedido();

    }

    public PedidoExibicaoDto atualizar(PedidoDto pedidoDto){
        Pedido pedido = new Pedido();
        BeanUtils.copyProperties(pedidoDto, pedido);
        Pedido pedidoAtualizado = pedidoRepository.save(pedido);
        return new PedidoExibicaoDto(pedidoAtualizado);
    }

}
