package br.com.demandaservicos.servico;

import br.com.demandaservicos.model.PrevisaoMediaMovel;
import br.com.demandaservicos.model.PrevisaoComparacao;
import br.com.demandaservicos.model.EstrategiaPrevisao;

public class FabricaPrevisao {
	public static EstrategiaPrevisao criarEstrategia(String tipo) {
	    if (tipo != null && tipo.equalsIgnoreCase("media")) {
	        return new PrevisaoMediaMovel();
	    } else if (tipo != null && tipo.equalsIgnoreCase("comparacao")) {
	        return new PrevisaoComparacao();
	    } else {
	        throw new IllegalArgumentException("Tipo de previsão inválido: " + tipo);
	    }
	}

}
