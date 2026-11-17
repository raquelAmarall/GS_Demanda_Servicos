package br.com.demandaservicos.model;
import br.com.demandaservicos.excecao.DadosInvalidosException;

public class DadosTerritorio {
	private double limiarAtencao;
	private double limiarAlerta;
	private double limiarCritico;
	private int populacaoAtendida;

	public DadosTerritorio(double limiarAtencao, double limiarAlerta,
			double limiarCritico, int populacaoAtendida) {
		this.setLimiares(limiarAtencao, limiarAlerta, limiarCritico);
		this.setPopulacaoAtendida(populacaoAtendida);
		
	}

	public void setLimiares(double atencao, double alerta, double critico) {
		if (atencao <= 0 || alerta <= atencao || critico <= alerta) {
			throw new DadosInvalidosException(
				"Limiares devem ser positivos e crescentes: atenção < alerta < crítico");
		}
		this.limiarAtencao = atencao;
		this.limiarAlerta = alerta;
		this.limiarCritico = critico;
	}

	public void setPopulacaoAtendida(int populacaoAtendida) {
		if (populacaoAtendida < 0) {
			throw new DadosInvalidosException("População não pode ser negativa");
		}
		this.populacaoAtendida = populacaoAtendida;
	}

	public double getLimiarAtencao() { return limiarAtencao; }
	public double getLimiarAlerta()  { return limiarAlerta; }
	public double getLimiarCritico() { return limiarCritico; }
	public int getPopulacaoAtendida() { return populacaoAtendida; }
}