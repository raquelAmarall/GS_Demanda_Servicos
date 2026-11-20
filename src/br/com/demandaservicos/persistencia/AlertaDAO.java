package br.com.demandaservicos.persistencia;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.demandaservicos.db.ConexaoBD;

import java.sql.PreparedStatement;
import java.sql.Timestamp;

import br.com.demandaservicos.model.Alerta;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.demandaservicos.model.NivelAlerta;

public class AlertaDAO {
	
	public void criarTabela() {

	    String sqlTabela = "CREATE TABLE alerta ("
	            + "id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "
	            + "tipo_sensor VARCHAR2(50) NOT NULL, "
	            + "valor_previsto NUMBER NOT NULL, "
	            + "limiar_ultrapassado NUMBER NOT NULL, "
	            + "nivel VARCHAR2(20) NOT NULL, "
	            + "data_hora TIMESTAMP NOT NULL"
	            + ")";

	    Connection conn = ConexaoBD.getInstancia().getConexao();

	    try (Statement stmt = conn.createStatement()) {

	        stmt.execute(sqlTabela);

	        System.out.println("Tabela 'alerta' criada com sucesso!");

	    } catch (SQLException e) {

	        if (e.getErrorCode() == 955) {
	            System.out.println("Tabela 'alerta' já existe.");

	        } else {
	            System.out.println("Erro ao criar tabela: " + e.getMessage());
	        }
	    }
	}
	
	private static final String SQL_INSERIR =
	        "INSERT INTO alerta (tipo_sensor, valor_previsto, "
	        + "limiar_ultrapassado, nivel, data_hora) "
	        + "VALUES (?, ?, ?, ?, ?)";
	
	public void inserir(Alerta alerta) {

	    Connection conn = ConexaoBD.getInstancia().getConexao();

	    try (PreparedStatement stmt = conn.prepareStatement(SQL_INSERIR)) {

	        stmt.setString(1, alerta.getTipoSensor());
	        stmt.setDouble(2, alerta.getValorPrevisto());
	        stmt.setDouble(3, alerta.getLimiarUltrapassado());
	        stmt.setString(4, alerta.getNivel().name());
	        stmt.setTimestamp(5, Timestamp.valueOf(alerta.getDataHora()));

	        stmt.executeUpdate();

	        System.out.println("Alerta inserido com sucesso!");

	    } catch (SQLException e) {
	        System.out.println("Erro ao inserir alerta: " + e.getMessage());
	    }
	}
	
	public List<Alerta> listarTodos() {

	    List<Alerta> alertas = new ArrayList<>();
	    String sql = "SELECT * FROM alerta";
	    
	    Connection conn = ConexaoBD.getInstancia().getConexao();

	    try (PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	    	
	    	while (rs.next()) {

	    	    int id = rs.getInt("id");
	    	    String tipoSensor = rs.getString("tipo_sensor");
	    	    double valorPrevisto = rs.getDouble("valor_previsto");
	    	    double limiarUltrapassado = rs.getDouble("limiar_ultrapassado");

	    	    String nivelTexto = rs.getString("nivel");
	    	    NivelAlerta nivel = NivelAlerta.valueOf(nivelTexto);

	    	    LocalDateTime dataHora =
	    	            rs.getTimestamp("data_hora").toLocalDateTime();

	    	    Alerta alerta = new Alerta(
	    	            id,
	    	            tipoSensor,
	    	            valorPrevisto,
	    	            limiarUltrapassado,
	    	            nivel,
	    	            dataHora
	    	    );

	    	    alertas.add(alerta);
	    	    
	    	} 
	     } catch (SQLException e) {
	    	    System.out.println("Erro ao listar alertas: " + e.getMessage());
	    	}

	    	return alertas;

	    }

	}

