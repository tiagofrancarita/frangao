package br.com.franca.frangao_assado.exception;

public class EnderecoNotFoundException extends RuntimeException {
    public EnderecoNotFoundException(String mensagem) {
        super(mensagem);
    }
}
