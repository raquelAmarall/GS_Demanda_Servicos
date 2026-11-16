package br.com.demandaservicos.model;

public abstract class ComponenteTerritorial {
	private int id;
	private String nome;
	private String regiao;
	private String status;
	
	public ComponenteTerritorial(int id, String nome, String regiao, String status) {
		this.setId(id);
		this.setNome(nome);
		this.setRegiao(regiao);
		this.setStatus(status);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		if (id > 0) {
			this.id = id;
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	 public void setNome(String nome) {
	        if (nome == null) {
	            System.out.println("Nome não informado.");
	            this.nome = "Nome não informado";

	        } else {
	            this.nome = nome;
	        }
	  }
	 
	public String getRegiao() {
		return regiao;
	}
	
	public void setRegiao(String regiao) {
		if (nome == null) {
			System.out.println("Região não informada.");
			this.regiao = "Região não informada";
			}else {
				this.regiao = regiao;
			}
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		if (status == null) {
			System.out.println("Status não informado.");
			this.status = "Status não informado.";
		}else {
			this.status = status;
		}
	}
	
	public abstract String verificarStatus();

}
