package br.com.demandaservicos.model;
import br.com.demandaservicos.excecao.FonteIndisponivelException;

import java.util.Random;

public class SensorConsultas implements Sensor {
	private final Random random = new Random();
	private boolean funcionando = true;

	@Override
	public int lerValor() {

	    if (!funcionando) {
	        throw new FonteIndisponivelException(
	                "Sensor de consultas indisponível!");
	    }

	    return 20 + random.nextInt(81);
	}

	@Override
	public boolean verificarFuncionamento() {
		return funcionando;
	}

	@Override
	public String tipo() {
		return "CONSULTAS";
	}

	@Override
	public void setFuncionando(boolean funcionando) {
		this.funcionando = funcionando;
	}
}