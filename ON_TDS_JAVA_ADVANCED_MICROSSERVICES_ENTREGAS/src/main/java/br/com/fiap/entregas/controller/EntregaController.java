package br.com.fiap.entregas.controller;

import br.com.fiap.entregas.dto.EntregaDto;
import br.com.fiap.entregas.dto.EntregaExibicaoDto;
import br.com.fiap.entregas.service.EntregaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    @Autowired
    private EntregaService service;

    @Autowired
    private Environment environment;

    @PostMapping
    public EntregaExibicaoDto criar(@RequestBody @Valid EntregaDto entregaDto){
        return service.criar(entregaDto);
    }

    @GetMapping("{numeroEntrega}")
    public ResponseEntity<EntregaExibicaoDto> buscarPorNumero(@PathVariable Long numeroEntrega){
        return ResponseEntity.ok(service.buscarPorNumero(numeroEntrega));
    }

    @GetMapping
    public ResponseEntity<List<EntregaExibicaoDto>> listarTodasAsEntregas(){
        return ResponseEntity.ok(service.listarTodasAsEntregas());
    }

    @DeleteMapping("{numeroEntrega}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long numeroEntrega){
        service.excluir(numeroEntrega);
    }

    @PutMapping
    public EntregaExibicaoDto atualizar(@RequestBody EntregaDto entregaDto){
        return service.atualizar(entregaDto);
    }

    @GetMapping("/porta")
    public ResponseEntity<String> mostrarPorta(){
        String porta = environment.getProperty("local.server.port");
        String mensagem = String.format("A porta utilizada é: %s", porta);
        return ResponseEntity.ok(mensagem);
    }

    @PutMapping("/{id}/transporte")
    public void colocarEmTransporte(@PathVariable Long id){
        service.colocarEmEntrega(id);
    }


}
