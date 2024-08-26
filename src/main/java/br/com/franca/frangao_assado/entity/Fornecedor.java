package br.com.franca.frangao_assado.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "fornecedor")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String tipoProduto;


    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    private Boolean ativo;
}
