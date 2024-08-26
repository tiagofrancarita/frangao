package br.com.franca.frangao_assado.exception;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String mensagem) {
        super(mensagem);
    }
}
