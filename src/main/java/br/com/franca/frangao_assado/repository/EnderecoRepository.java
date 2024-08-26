package br.com.franca.frangao_assado.repository;

import br.com.franca.frangao_assado.entity.Endereco;
import br.com.franca.frangao_assado.entity.enums.TipoEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {

    List<Endereco> findByTipoEnderecoAndEntidadeId(TipoEndereco tipoEndereco, UUID entidadeId);

    void deleteByEntidadeIdAndTipoEndereco(UUID entidadeId, TipoEndereco tipoEndereco);

    Optional<Endereco> findByEntidadeIdAndTipoEndereco(UUID clienteId, TipoEndereco tipoEndereco);
}
