package br.com.demandaservicos.model;

import java.util.List;

import br.com.demandaservicos.excecao.DadosInsuficientesException;

public class PrevisaoComparacao extends EstrategiaPrevisao {

    public PrevisaoComparacao() {
        super(3);
    }
    
    @Override
    public double prever(List<Medicao> medicoes) {

        int quantidade = getQuantidadePorPeriodo();

        if (medicoes == null || medicoes.size() < quantidade * 2) {
            throw new DadosInsuficientesException(
                    "São necessárias pelo menos "
                    + (quantidade * 2)
                    + " medições para comparar os períodos.");
        }
        
        int inicioPeriodoAnterior = medicoes.size() - (quantidade * 2);
        int inicioPeriodoAtual = medicoes.size() - quantidade;

        double somaAnterior = 0;
        double somaAtual = 0;
        
        for (int i = inicioPeriodoAnterior; i < inicioPeriodoAtual; i++) {
            somaAnterior += medicoes.get(i).getValor();
        }

        for (int i = inicioPeriodoAtual; i < medicoes.size(); i++) {
            somaAtual += medicoes.get(i).getValor();
        }
        
        double mediaAnterior = somaAnterior / quantidade;
        double mediaAtual = somaAtual / quantidade;
        
        double variacao = mediaAtual - mediaAnterior;

        return mediaAtual + variacao;

    }

}