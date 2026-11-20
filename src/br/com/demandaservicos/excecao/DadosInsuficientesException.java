package br.com.demandaservicos.excecao;

public class DadosInsuficientesException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    public DadosInsuficientesException(String mensagem) {
        super(mensagem);
    }
}