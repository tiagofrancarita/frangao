package br.com.franca.frangao_assado.entity.dto;

import br.com.franca.frangao_assado.entity.Cliente;
import br.com.franca.frangao_assado.entity.Endereco;

public class ClienteEnderecoDTO {

    private Cliente cliente;
    private Endereco endereco;

    // Getters e Setters

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
