package br.com.demandaservicos.model;

import java.util.Random;

public class SensorConsultas implements Sensor {
	private final Random random = new Random();
	private boolean funcionando = true;

	@Override
	public int lerValor() {
		// faixa realista de consultas por dia
		return 20 + random.nextInt(81);   // gera de 20 a 100
	}

	@Override
	public boolean verificarFuncionamento() {
		return funcionando;
	}

	@Override
	public String tipo() {
		return "CONSULTAS";
	}

	public void setFuncionando(boolean funcionando) {
		this.funcionando = funcionando;
	}
}