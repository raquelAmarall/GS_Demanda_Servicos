package br.com.demandaservicos.model;

import java.time.LocalDateTime;

import br.com.demandaservicos.excecao.DadosInvalidosException;



public class Medicao {
	private int id;
	private String tipoSensor;
	private int valor;
	private LocalDateTime dataHora;

	public Medicao(String tipoSensor, int valor) {
		setTipoSensor(tipoSensor);
		setValor(valor);
		this.dataHora = LocalDateTime.now();
	}
	
	public Medicao(int id, String tipoSensor, int valor, LocalDateTime dataHora) {
	    this.id = id;
	    setTipoSensor(tipoSensor);
	    setValor(valor);
	    this.dataHora = dataHora;
	}

	public int getId() { return id; }
	public String getTipoSensor() { return tipoSensor; }
	public int getValor() { return valor; }
	public LocalDateTime getDataHora() { return dataHora; }

	private void setTipoSensor(String tipoSensor) {
		if (tipoSensor == null || tipoSensor.isBlank()) {
			throw new DadosInvalidosException("Tipo do sensor não informado");
		}
		this.tipoSensor = tipoSensor;
	}

	private void setValor(int valor) {
		if (valor < 0) {
			throw new DadosInvalidosException(
				"Medição de " + tipoSensor + " não pode ser negativa: " + valor);
		}
		this.valor = valor;
	}
}