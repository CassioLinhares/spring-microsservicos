package br.com.fiap.entregas.exception;

public class EntregaNaoEncotradaException extends RuntimeException {
    public EntregaNaoEncotradaException(String mensagem) {
        super(mensagem);
    }
}
