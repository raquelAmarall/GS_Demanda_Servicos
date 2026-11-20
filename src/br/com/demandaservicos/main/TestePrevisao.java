package br.com.demandaservicos.main;

import java.util.List;
import br.com.demandaservicos.model.Medicao;
import br.com.demandaservicos.model.PrevisaoMediaMovel;

public class TestePrevisao {

	public static void main(String[] args) {
		List<Medicao> medicoes = List.of(
			    new Medicao("SensorExames", 40),
			    new Medicao("SensorExames", 50),
			    new Medicao("SensorExames", 60),
			    new Medicao("SensorExames", 70),
			    new Medicao("SensorExames", 80)
			);

			PrevisaoMediaMovel estrategia = new PrevisaoMediaMovel();

			double resultado = estrategia.prever(medicoes);

			System.out.println("Previsão: " + resultado);
	}

}
