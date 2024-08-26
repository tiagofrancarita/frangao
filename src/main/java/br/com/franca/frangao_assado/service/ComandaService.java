package br.com.franca.frangao_assado.service;

import br.com.franca.frangao_assado.entity.Comanda;
import br.com.franca.frangao_assado.entity.dto.ComandaRelatorioDTO;
import br.com.franca.frangao_assado.repository.ComandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ComandaService {

    @Autowired
    private ComandaRepository comandaRepository;

    public ComandaRelatorioDTO obterRelatorioPorPeriodo(Date startDate, Date endDate) {
        List<Comanda> comandas = comandaRepository.findComandasByPeriodo(startDate, endDate);
        Double valorTotal = comandaRepository.findValorTotalByPeriodo(startDate, endDate);

        ComandaRelatorioDTO relatorio = new ComandaRelatorioDTO();
        relatorio.setComandas(comandas);
        relatorio.setValorTotal(valorTotal);

        return relatorio;
    }
}