package br.com.demandaservicos.excecao;

public class FonteIndisponivelException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FonteIndisponivelException(String mensagem) {
        super(mensagem);
    }
}