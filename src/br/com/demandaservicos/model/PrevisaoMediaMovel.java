package br.com.demandaservicos.model;

import java.util.List;

import br.com.demandaservicos.excecao.DadosInsuficientesException;

public class PrevisaoMediaMovel extends EstrategiaPrevisao {
	
	public PrevisaoMediaMovel() {
	    super(3);
	}
	
	
	@Override
	public double prever(List<Medicao> medicoes) {

	    double soma = 0;
	    int quantidade = getQuantidadePorPeriodo();

	    if (medicoes == null || medicoes.size() < quantidade) {
	        throw new DadosInsuficientesException(
	                "São necessárias pelo menos "
	                + quantidade + " medições para calcular a previsão.");
	    }

	    for (int i = medicoes.size() - quantidade;
	            i < medicoes.size(); i++) {

	        soma += medicoes.get(i).getValor();
	    }

	    return soma / quantidade;
	}

}
