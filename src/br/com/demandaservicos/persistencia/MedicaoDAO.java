package br.com.demandaservicos.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import br.com.demandaservicos.db.ConexaoBD;
import java.sql.Timestamp;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;

import br.com.demandaservicos.model.Medicao;

public class MedicaoDAO {
	
	public void criarTabela() {

	    String sqlTabela =
	            "CREATE TABLE medicao (" +
	            "id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
	            "tipo_sensor VARCHAR2(50) NOT NULL, " +
	            "valor NUMBER NOT NULL, " +
	            "data_hora TIMESTAMP NOT NULL" +
	            ")";

	    Connection conn = ConexaoBD.getInstancia().getConexao();

	    try (Statement stmt = conn.createStatement()) {

	        stmt.execute(sqlTabela);

	        System.out.println("Tabela 'medicao' criada com sucesso!");

	    } catch (SQLException e) {

	        if (e.getErrorCode() == 955) {

	            System.out.println("Tabela 'medicao' já existe.");

	        } else {

	            System.out.println("Erro ao criar tabela: " + e.getMessage());

	        }

	    }
	}
	
	private static final String SQL_INSERIR =
	        "INSERT INTO medicao (tipo_sensor, valor, data_hora) "
	        + "VALUES (?, ?, ?)";
	
	public void inserir(Medicao medicao) {

	    Connection conn = ConexaoBD.getInstancia().getConexao();

	    try (PreparedStatement stmt = conn.prepareStatement(SQL_INSERIR)) {

	        stmt.setString(1, medicao.getTipoSensor());
	        stmt.setInt(2, medicao.getValor());
	        stmt.setTimestamp(3, Timestamp.valueOf(medicao.getDataHora()));

	        stmt.executeUpdate();

	        System.out.println("Medição inserida com sucesso!");

	    } catch (SQLException e) {

	        System.out.println("Erro ao inserir medição: " + e.getMessage());

	    }
	}
	
	public List<Medicao> listarTodas() {

	    List<Medicao> medicoes = new ArrayList<>();

	    String sql = "SELECT * FROM medicao";

	    Connection conn = ConexaoBD.getInstancia().getConexao();

	    try (PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {

	            int id = rs.getInt("id");
	            String tipoSensor = rs.getString("tipo_sensor");
	            int valor = rs.getInt("valor");

	            LocalDateTime dataHora = rs.getTimestamp("data_hora").toLocalDateTime();

	            Medicao medicao = new Medicao(id, tipoSensor, valor, dataHora);

	            medicoes.add(medicao);
	        }

	    } catch (SQLException e) {
	        System.out.println("Erro ao listar medições: " + e.getMessage());
	    }

	    return medicoes;
	}
	

}
