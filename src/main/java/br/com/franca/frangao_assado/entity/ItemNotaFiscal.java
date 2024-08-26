package br.com.franca.frangao_assado.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "item_nota_fiscal")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemNotaFiscal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;
    private Double precoUnitario;
    private Double precoTotal;

    @ManyToOne
    @JoinColumn(name = "nota_fiscal_id")
    private NotaFiscalEntrada notaFiscal;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
}
