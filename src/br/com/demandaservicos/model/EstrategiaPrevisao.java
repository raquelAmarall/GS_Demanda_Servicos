package br.com.demandaservicos.model;

import java.util.List;

public abstract class EstrategiaPrevisao {

    public abstract double prever(List<Medicao> medicoes);

}