package br.com.demandaservicos.model;

import java.time.LocalDateTime;

public class Alerta {
	private int id;
	private String tipoSensor;
	private double valorPrevisto;
	private double limiarUltrapassado;
	private NivelAlerta nivel;
	private LocalDateTime dataHora;
	
	public Alerta(String tipoSensor, double valorPrevisto, double limiarUltrapassado, NivelAlerta nivel) {

	    this.tipoSensor = tipoSensor;
	    this.valorPrevisto = valorPrevisto;
	    this.limiarUltrapassado = limiarUltrapassado;
	    this.nivel = nivel;
	    this.dataHora = LocalDateTime.now();
	}
	
	public Alerta(int id, String tipoSensor, double valorPrevisto, double limiarUltrapassado, NivelAlerta nivel, LocalDateTime dataHora) {
	    this.id = id;
	    this.tipoSensor = tipoSensor;
	    this.valorPrevisto = valorPrevisto;
	    this.limiarUltrapassado = limiarUltrapassado;
	    this.nivel = nivel;
	    this.dataHora = dataHora;
	}
	
	public int getId() {
	    return id;
	}

	public String getTipoSensor() {
	    return tipoSensor;
	}

	public double getValorPrevisto() {
	    return valorPrevisto;
	}

	public double getLimiarUltrapassado() {
	    return limiarUltrapassado;
	}

	public NivelAlerta getNivel() {
	    return nivel;
	}

	public LocalDateTime getDataHora() {
	    return dataHora;
	}
	
	public String getMotivo() {
	    return "A previsão de " + valorPrevisto
	            + " para " + tipoSensor
	            + " ultrapassou o limiar de "
	            + limiarUltrapassado
	            + ", gerando um alerta de nível " + nivel + ".";
	}

}
