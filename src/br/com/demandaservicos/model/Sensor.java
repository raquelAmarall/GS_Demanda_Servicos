package br.com.demandaservicos.model;

public interface Sensor {
	int lerValor();
	boolean verificarFuncionamento();
	String tipo();
	void setFuncionando(boolean funcionando);

}
