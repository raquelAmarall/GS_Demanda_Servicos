package br.com.demandaservicos.model;

public class UBS extends ComponenteTerritorial {
	private int capacidadeConsultas;
	private int capacidadeVacinacao;
	private int capacidadeExames;
	private int consultasHoje;
	private int vacinacoesHoje;
	private int examesHoje;
	
	public UBS(int id, String nome, String regiao, String status, int capacidadeConsultas, int capacidadeVacinacao, int capacidadeExames, int consultasHoje, int vacinacoesHoje, int examesHoje) {
		super(id, nome, regiao, status);
		this.setCapacidadeConsultas(capacidadeConsultas);
		this.setCapacidadeVacinacao(capacidadeVacinacao);
		this.setCapacidadeExames(capacidadeExames);
		this.setConsultasHoje(consultasHoje);
		this.setVacinacoesHoje(vacinacoesHoje);
		this.setExamesHoje(examesHoje);
	}
	
	public int getCapacidadeConsultas() {
		return capacidadeConsultas;
	}
	
	public void setCapacidadeConsultas(int capacidadeConsultas) {
		if (capacidadeConsultas > 0) {
			this.capacidadeConsultas = capacidadeConsultas;
		}
	}
	
	public int getCapacidadeVacinacao() {
		return capacidadeVacinacao;
	}
	
	public void setCapacidadeVacinacao(int capacidadeVacinacao) {
		if (capacidadeVacinacao > 0) {
			this.capacidadeVacinacao = capacidadeVacinacao;
		}
	}
	
	public int getCapacidadeExames() {
		return capacidadeExames;
	}
	
	public void setCapacidadeExames(int capacidadeExames) {
		if (capacidadeExames > 0) {
			this.capacidadeExames = capacidadeExames;
		}
	}

	public int getConsultasHoje() {
		return consultasHoje;
	}

	public void setConsultasHoje(int consultasHoje) {
		if (consultasHoje >= 0) {
			this.consultasHoje = consultasHoje;
		}
	}

	public int getVacinacoesHoje() {
		return vacinacoesHoje;
	}

	public void setVacinacoesHoje(int vacinacoesHoje) {
		if (vacinacoesHoje >= 0) {
			this.vacinacoesHoje = vacinacoesHoje;
		}
	}

	public int getExamesHoje() {
		return examesHoje;
	}

	public void setExamesHoje(int examesHoje) {
		if (examesHoje >= 0) {
			this.examesHoje = examesHoje;
		}
	}

	@Override
	public String verificarStatus() {
	    double ocupConsultas  = (double) consultasHoje  / capacidadeConsultas;
	    double ocupVacinacao  = (double) vacinacoesHoje / capacidadeVacinacao;
	    double ocupExames     = (double) examesHoje     / capacidadeExames;

	    double pior = Math.max(ocupConsultas, Math.max(ocupVacinacao, ocupExames));

	    String novoStatus;
	    if (pior >= 1.0) {
	        novoStatus = "SOBRECARREGADA";
	    } else if (pior >= 0.8) {
	        novoStatus = "ATENÇÃO";
	    } else {
	        novoStatus = "NORMAL";
	    }

	    setStatus(novoStatus);
	    return novoStatus;
	}
	
	

}
