package br.com.demandaservicos.main;
import br.com.demandaservicos.persistencia.MedicaoDAO;

import br.com.demandaservicos.db.ConexaoBD;
import br.com.demandaservicos.model.Medicao;

import java.util.List;
import br.com.demandaservicos.persistencia.MedicaoDAO;
import br.com.demandaservicos.db.ConexaoBD;
import br.com.demandaservicos.model.Medicao;

public class TesteConexao {

	public static void main(String[] args) {

	    ConexaoBD.getInstancia().conectar();

	    MedicaoDAO dao = new MedicaoDAO();

	    List<Medicao> medicoes = dao.listarTodas();

	    for (Medicao m : medicoes) {
	        System.out.println(
	            m.getId() + " | " +
	            m.getTipoSensor() + " | " +
	            m.getValor() + " | " +
	            m.getDataHora()
	        );
	    }

	    ConexaoBD.getInstancia().desconectar();
	}
}