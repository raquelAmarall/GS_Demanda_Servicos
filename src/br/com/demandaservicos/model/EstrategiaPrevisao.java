package br.com.demandaservicos.model;

import java.util.List;

public abstract class EstrategiaPrevisao {

    private final int quantidadePorPeriodo;

    protected EstrategiaPrevisao(int quantidadePorPeriodo) {
        this.quantidadePorPeriodo = quantidadePorPeriodo;
    }

    protected int getQuantidadePorPeriodo() {
        return quantidadePorPeriodo;
    }

    public abstract double prever(List<Medicao> medicoes);
}