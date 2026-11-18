package br.com.demandaservicos.excecao;

public class DadosInvalidosException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	  public DadosInvalidosException(String mensagem) {
	        super(mensagem);
	    }

}
