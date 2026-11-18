package br.com.demandaservicos.model;

import java.util.List;

import br.com.demandaservicos.excecao.DadosInsuficientesException;

public class PrevisaoMediaMovel extends EstrategiaPrevisao {
	
	
	@Override
	public double prever(List<Medicao> medicoes) {
		double soma = 0;
		if (medicoes == null || medicoes.size() < 3) {
		    throw new DadosInsuficientesException(
		        "São necessárias pelo menos 3 medições para calcular a previsão.");
		}
		for (int i = medicoes.size() - 3; i < medicoes.size(); i++) {
			soma += medicoes.get(i).getValor();
		}
	    return soma / 3;
	}

}
