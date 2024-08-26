package br.com.franca.frangao_assado.entity.dto;

import java.time.LocalDate;
import java.util.Date;

public class ClienteAtualizacaoDTO {

    private String nome;
    private String email;
    private String telefone;
    private Date dataNascimento;
    private EnderecoDTO endereco;

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }
    public EnderecoDTO getEndereco() { return endereco; }
    public void setEndereco(EnderecoDTO endereco) { this.endereco = endereco; }
}