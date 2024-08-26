package br.com.franca.frangao_assado.repository;

import br.com.franca.frangao_assado.entity.ItemComanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemComandaRepository extends JpaRepository<ItemComanda, Long> {
}
