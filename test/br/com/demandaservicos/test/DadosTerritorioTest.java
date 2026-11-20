package br.com.demandaservicos.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.demandaservicos.model.DadosTerritorio;
import br.com.demandaservicos.excecao.DadosInvalidosException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

import br.com.demandaservicos.model.Medicao;
import br.com.demandaservicos.model.PrevisaoMediaMovel;

import br.com.demandaservicos.model.PrevisaoComparacao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

import br.com.demandaservicos.persistencia.MedicaoDAO;
import br.com.demandaservicos.persistencia.AlertaDAO;
import br.com.demandaservicos.servico.ServicoMonitoramento;

import br.com.demandaservicos.model.Alerta;

public class DadosTerritorioTest {

    @Test
    public void deveRejeitarPopulacaoNegativa() {

        // Arrange
        DadosTerritorio territorio = new DadosTerritorio(20, 40, 60, 10000);

        // Act e Assert
        assertThrows(DadosInvalidosException.class, () -> {
            territorio.setPopulacaoAtendida(-100);
        });
    }
    
    @Test
    public void deveRejeitarLimiaresInvalidos() {

        // Arrange
        DadosTerritorio territorio = new DadosTerritorio(20, 40, 60, 10000);

        // Act e Assert
        assertThrows(DadosInvalidosException.class, () -> {
            territorio.setLimiares(50, 40, 60);
        });
    }
    
    @Test
    public void deveCalcularMediaDasUltimasTresMedicoes() {

        // Arrange
        PrevisaoMediaMovel estrategia = new PrevisaoMediaMovel();

        List<Medicao> medicoes = List.of(
            new Medicao("CONSULTAS", 10),
            new Medicao("CONSULTAS", 20),
            new Medicao("CONSULTAS", 30),
            new Medicao("CONSULTAS", 40),
            new Medicao("CONSULTAS", 50)
        );

        // Act
        double resultado = estrategia.prever(medicoes);

        // Assert
        assertEquals(40.0, resultado, 0.001);
    }
    
    @Test
    public void deveCalcularPrevisaoPorComparacao() {

        // Arrange
        PrevisaoComparacao estrategia = new PrevisaoComparacao();

        List<Medicao> medicoes = List.of(
            new Medicao("CONSULTAS", 20),
            new Medicao("CONSULTAS", 25),
            new Medicao("CONSULTAS", 30),
            new Medicao("CONSULTAS", 30),
            new Medicao("CONSULTAS", 35),
            new Medicao("CONSULTAS", 40)
        );

        // Act
        double resultado = estrategia.prever(medicoes);

        // Assert
        assertEquals(45.0, resultado, 0.001);
    }
    
    @Test
    public void deveEmitirAlertaUtilizandoDAOSimulado() {

        // Arrange
        MedicaoDAO medicaoDAO = mock(MedicaoDAO.class);
        AlertaDAO alertaDAO = mock(AlertaDAO.class);

        DadosTerritorio territorio = new DadosTerritorio(20, 40, 60, 10000);

        PrevisaoMediaMovel estrategia = new PrevisaoMediaMovel();

        List<Medicao> medicoes = List.of(
            new Medicao("CONSULTAS", 70),
            new Medicao("CONSULTAS", 80),
            new Medicao("CONSULTAS", 90)
        );

        when(medicaoDAO.buscarHistoricoPorSensor("CONSULTAS"))
            .thenReturn(medicoes);

        ServicoMonitoramento servico = new ServicoMonitoramento(
            medicaoDAO,
            alertaDAO,
            territorio,
            estrategia
        );

        // Act
        servico.verificarEEmitirAlerta("CONSULTAS");

        // Assert
        verify(alertaDAO).inserir(any(Alerta.class));
    }
}