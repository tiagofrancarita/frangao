package br.com.franca.frangao_assado.repository;

import br.com.franca.frangao_assado.entity.TipoEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEntregaRepository extends JpaRepository<TipoEntrega, Long> {
}
