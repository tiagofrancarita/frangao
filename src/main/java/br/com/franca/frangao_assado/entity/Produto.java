package br.com.franca.frangao_assado.entity;

import br.com.franca.frangao_assado.entity.enums.UnidadeMedida;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "produto")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipo;
    private Integer quantidadeEmEstoque;
    private Double precoUnitario;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @Temporal(TemporalType.DATE)
    private Date dataCompra;

    @Temporal(TemporalType.DATE)
    private Date dataVencimento;

    private String marca;

    @Enumerated(EnumType.STRING)
    private UnidadeMedida unidadeMedida;
}
