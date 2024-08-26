package br.com.franca.frangao_assado.exception;

public class ErrorResponse {

    private String mensagem;

    // Construtor
    public ErrorResponse(String mensagem) {
        this.mensagem = mensagem;
    }

    // Getter e Setter
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}