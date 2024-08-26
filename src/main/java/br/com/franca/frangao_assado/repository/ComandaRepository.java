package br.com.franca.frangao_assado.repository;

import br.com.franca.frangao_assado.entity.Comanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Long> {

    @Query("SELECT c FROM Comanda c WHERE c.dataVenda BETWEEN :startDate AND :endDate")
    List<Comanda> findComandasByPeriodo(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT SUM(c.valorTotal) FROM Comanda c WHERE c.dataVenda BETWEEN :startDate AND :endDate")
    Double findValorTotalByPeriodo(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
