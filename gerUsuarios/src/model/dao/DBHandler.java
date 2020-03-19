package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;

import model.ModelException;

public class DBHandler {
	
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	public DBHandler() throws ModelException {
		connection = MySQLConnectionFactory.getConnection();
	}
	
	public void prepareStatement(String sqlInsert) throws ModelException {
		try {
			preparedStatement = connection.prepareStatement(sqlInsert);
		} catch (SQLException e) {
			close();
			throw new ModelException("Erro ao preparar a SQL", e);
		}
	}

	public void setNull(int i) throws ModelException {
		validatePreparedStatement();
		
		try {
			preparedStatement.setNull(i, Types.INTEGER);
		} catch (SQLException e) {
			close();
			throw new ModelException("Erro ao atribuir string", e);
		}
	}
	
	public void setString(int i, String value) throws ModelException {
		validatePreparedStatement();
		
		try {
			preparedStatement.setString(i, value);
		} catch (SQLException e) {
			close();
			throw new ModelException("Erro ao atribuir string", e);
		}
	}

	public void setInt(int i, int value) throws ModelException {
		validatePreparedStatement();
		
		try {
			preparedStatement.setInt(i, value);
		} catch (SQLException e) {
			close();
			throw new ModelException("Erro ao atribuir inteiro", e);
		}
	}
	
	public void setLong(int i, long value) throws ModelException {
		validatePreparedStatement();
		
		try {
			preparedStatement.setLong(i, value);
		} catch (SQLException e) {
			close();
			throw new ModelException("Erro ao atribuir inteiro", e);
		}
	}

	public int executeUpdate() throws ModelException  {
		validatePreparedStatement();
		try {
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw formatSQLErrorMessage(e);
		} finally{
			close();
		}
	}

	private ModelException formatSQLErrorMessage(SQLException e) {
		String s = "foreign key constraint fails";
		if(e.getMessage().contains(s))
			return new ModelException("Erro ao Excluir este registro, pois este registro é chave em outra tabela", e);
		else
			return new ModelException("Erro ao executar sql");
	}
	
	public void createStatement() throws ModelException {
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			throw new ModelException("Erro ao criar o statement", e);
		}
	}
	
	public void executeQuery(String sql) throws ModelException {
		validateStatement();
		
		try {
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			close();
			throw new ModelException("Erro ao executar SQL", e);
		}
	}
	
	public void executeQuery() throws ModelException {
		validatePreparedStatement();
		
		try {
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			close();
			throw formatSQLErrorMessage(e);
		}
	}
	
	public boolean next() throws ModelException {
		validateResultSet();
		
		try {
			return resultSet.next();
		} catch (SQLException e) {
			close();
			throw new ModelException("Erro ao chamar prÃ³ximo registro", e);
		}
	}
	
	public int getInt(String column) throws ModelException {
		try {
			return resultSet.getInt(column);
		} catch (SQLException e) {
			close();
			throw new ModelException("Erro ao chamar getInt", e);
		}
	}
	
	public String getString(String column) throws ModelException {
		try {
			return resultSet.getString(column);
		} catch (SQLException e) {
			close();
			throw new ModelException("Erro ao chamar getString", e);
		}
	}
	
	public Date getDate(String column) throws ModelException {
		try {
			return resultSet.getDate(column);
		} catch (SQLException e) {
			close();
			throw new ModelException("Erro ao chamar getDate", e);
		}
	}
	
	private void close() throws ModelException {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
			
			if (statement != null)
				statement.close();
			
			if (resultSet != null)
				resultSet.close();
			
			connection.close();
		} catch (SQLException e) {
			throw new ModelException("Erro ao fechar recursos", e);
		}
	}
	
	private void validatePreparedStatement() throws ModelException {
		if (preparedStatement == null)
			throw new ModelException("Preparede Statement nÃ£o foi criado");
	}

	private void validateStatement() throws ModelException {
		if (statement == null)
			throw new ModelException("Statement nÃ£o foi criado");
	}

	private void validateResultSet() throws ModelException {
		if (resultSet == null)
			throw new ModelException("Result set nÃ£o foi criado");
	}

	public void setDouble(int i, double value) throws ModelException {
		validatePreparedStatement();
		
		try {
			preparedStatement.setDouble(i, value);
		} catch (SQLException e) {
			close();
			throw new ModelException("Erro ao atribuir Double", e);
		}
	}

	public void setDate(int i, Date data) throws ModelException {
		validatePreparedStatement();
		
		try {
			preparedStatement.setDate(i, (java.sql.Date)data);
		} catch (SQLException e) {
			close();
			throw new ModelException("Erro ao atribuir Double", e);
		}
	}

	public Double getDouble(String column) throws ModelException {
		try {
			return resultSet.getDouble(column);
		} catch (SQLException e) {
			close();
			throw new ModelException("Erro ao chamar getDouble", e);
		}
	}
}
