package br.com.franca.frangao_assado.entity.dto;

import br.com.franca.frangao_assado.entity.Comanda;

import java.util.List;

public class ComandaRelatorioDTO {

    private List<Comanda> comandas;
    private Double valorTotal;

    // Getters e Setters

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

}
