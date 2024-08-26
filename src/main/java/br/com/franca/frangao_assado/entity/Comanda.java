package br.com.franca.frangao_assado.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comanda")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comanda implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Temporal(TemporalType.DATE)
    private Date dataVenda;

    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id")
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "tipo_entrega_id")
    private TipoEntrega tipoEntrega;

    private Double valorTotal;

    @OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemComanda> itens;
}
