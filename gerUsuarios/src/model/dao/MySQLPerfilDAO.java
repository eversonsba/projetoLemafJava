package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.Perfil;
import model.Cargo;
import model.ModelException;

public class MySQLPerfilDAO implements DAOPerfil {

	MySQLUsuarioDAO daou = new MySQLUsuarioDAO();

	@Override
	public boolean save(Perfil perfil) throws ModelException {
		DBHandler db = new DBHandler();

		String sqlInsert = "INSERT INTO perfis VALUE" + "(Default,?,?);";
		System.out.println(perfil.getUsuario().getCpf());
		db.prepareStatement(sqlInsert);
		db.setString(1, perfil.getNome());
		db.setString(2, perfil.getUsuario().getCpf());
		System.out.println(sqlInsert);
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean update(Perfil perfil) throws ModelException {
		DBHandler db = new DBHandler();

		String sqlUpdate = "UPDATE perfis" + " SET nome= ?, cpf_usuario = ? " + " WHERE id = ?;";

		db.prepareStatement(sqlUpdate);

		db.setString(1, perfil.getNome());
		db.setString(2, perfil.getUsuario().getCpf());
		db.setLong(3, perfil.getId());
		System.out.println(sqlUpdate);
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Perfil perfil) throws ModelException {
		DBHandler db = new DBHandler();

		String sqlDelete = "DELETE FROM perfis " + " WHERE id = ?;";

		db.prepareStatement(sqlDelete); 
		db.setLong(1, perfil.getId());

		return db.executeUpdate() > 0;
	}

	@Override
	public List<Perfil> listAll() throws ModelException {
		DBHandler db = new DBHandler();

		List<Perfil> perfis = new ArrayList<Perfil>();

		// Declara um instrução SQL
		String sqlQuery = " SELECT * FROM perfis order by nome; ";

		db.createStatement();

		db.executeQuery(sqlQuery);

		while (db.next()) {
			Perfil p = createPerfil(db);

			perfis.add(p);
		}

		return perfis;
	}

	@Override
	public Perfil findById(Long id) throws ModelException {
		DBHandler db = new DBHandler();

		String sqlQuery = "SELECT * FROM perfis where id = ?; ";

		db.createStatement();
		db.prepareStatement(sqlQuery);
		db.setLong(1, id);
		System.out.println(sqlQuery);
		db.executeQuery();

		Perfil p = null;
		while (db.next()) {
			p = createPerfil(db);
			break;
		}

		return p;

	}

	private Perfil createPerfil(DBHandler db) throws ModelException {
		Perfil p = new Perfil();
		p.setId(Integer.parseInt(db.getString("id")));
		p.setNome(db.getString("nome"));
		p.setUsuario(daou.findByCpf(db.getString("cpf_usuario")));
		return p;
	}
}
