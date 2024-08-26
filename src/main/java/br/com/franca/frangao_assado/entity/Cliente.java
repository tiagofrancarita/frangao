package br.com.franca.frangao_assado.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "cliente")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Nome não pode ser vazio")
    @Size(min = 10, message = "Nome deve ter pelo menos 10 caracteres")
    @Column(nullable = false, length = 200, name = "nome")
    private String nome;

    @NotBlank(message = "E-mail não pode ser vazio")
    @Email(message = "E-mail inválido")
    @Column(unique = true)
    private String email;

    @Temporal(TemporalType.DATE)
    @Past(message = "Data de nascimento deve ser anterior à data atual")
    private Date dataNascimento;

    @NotBlank(message = "Telefone não pode ser vazio")
    @Size(min = 10, message = "Telefone deve ter pelo menos 10 caracteres")
    @Column(nullable = false, length = 20)
    private String telefone;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(email, cliente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}