package br.com.demandaservicos.model;

import java.util.Random;

import br.com.demandaservicos.excecao.FonteIndisponivelException;

public class SensorExames implements Sensor {
	private final Random random = new Random();
	private boolean funcionando = true;

	@Override
	public int lerValor() {

	    if (!funcionando) {
	        throw new FonteIndisponivelException(
	                "Sensor de exames indisponível!");
	    }

	    return 20 + random.nextInt(81);
	}

	@Override
	public boolean verificarFuncionamento() {
		return funcionando;
	}

	@Override
	public String tipo() {
		return "EXAMES";
	}

	@Override
	public void setFuncionando(boolean funcionando) {
		this.funcionando = funcionando;
	}
}