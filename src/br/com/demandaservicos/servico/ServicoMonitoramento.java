package br.com.demandaservicos.servico;

import br.com.demandaservicos.model.DadosTerritorio;
import br.com.demandaservicos.model.EstrategiaPrevisao;
import br.com.demandaservicos.persistencia.AlertaDAO;
import br.com.demandaservicos.persistencia.MedicaoDAO;

import java.util.List;
import br.com.demandaservicos.model.Medicao;

import br.com.demandaservicos.model.Alerta;
import br.com.demandaservicos.model.NivelAlerta;

public class ServicoMonitoramento {
	private final MedicaoDAO medicaoDAO;
	private final AlertaDAO alertaDAO;
	private final DadosTerritorio dadosTerritorio;
	private final EstrategiaPrevisao estrategiaPrevisao;
	
	public ServicoMonitoramento(
	        MedicaoDAO medicaoDAO,
	        AlertaDAO alertaDAO,
	        DadosTerritorio dadosTerritorio,
	        EstrategiaPrevisao estrategiaPrevisao) {

	    this.medicaoDAO = medicaoDAO;
	    this.alertaDAO = alertaDAO;
	    this.dadosTerritorio = dadosTerritorio;
	    this.estrategiaPrevisao = estrategiaPrevisao;
	}
	
	public double analisarSensor(String tipoSensor) {
		  List<Medicao> medicoes =
		            medicaoDAO.buscarHistoricoPorSensor(tipoSensor);

		    double previsao = estrategiaPrevisao.prever(medicoes);

		    return previsao;
		
	}
	
	public void verificarEEmitirAlerta(String tipoSensor) {

	    double previsao = analisarSensor(tipoSensor);

	    NivelAlerta nivel = null;
	    double limiarUltrapassado = 0;

	    if (previsao > dadosTerritorio.getLimiarCritico()) {

	        nivel = NivelAlerta.CRITICO;
	        limiarUltrapassado = dadosTerritorio.getLimiarCritico();

	    } else if (previsao > dadosTerritorio.getLimiarAlerta()) {

	        nivel = NivelAlerta.ALERTA;
	        limiarUltrapassado = dadosTerritorio.getLimiarAlerta();

	    } else if (previsao > dadosTerritorio.getLimiarAtencao()) {

	        nivel = NivelAlerta.ATENCAO;
	        limiarUltrapassado = dadosTerritorio.getLimiarAtencao();
	    }

	    if (nivel != null) {

	        Alerta alerta = new Alerta(
	                tipoSensor,
	                previsao,
	                limiarUltrapassado,
	                nivel
	        );
	       
	        
	        System.out.println("=== ALERTA EMITIDO ===");
	        System.out.println("Sensor: " + tipoSensor);
	        System.out.println("Nível: " + nivel);
	        System.out.println("Valor previsto: " + previsao);
	        System.out.println("Limiar ultrapassado: " + limiarUltrapassado);

	        alertaDAO.inserir(alerta);
	        
	        System.out.println(alerta.getMotivo());

	    } else {
	        System.out.println("Nenhum alerta necessário para " + tipoSensor);
	    }
	}

}
