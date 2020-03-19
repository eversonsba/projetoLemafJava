package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.Cargo;
import model.ModelException;
import model.Perfil;

public class MySQLCargoDAO implements DAOCargo {

	@Override
	public boolean save(Cargo cargo) throws ModelException {
		DBHandler db = new DBHandler();

		String sqlInsert = "INSERT INTO cargos VALUES" + "(Default,?)";

		db.prepareStatement(sqlInsert);
		db.setString(1, cargo.getNome());
		System.out.println(sqlInsert);
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean update(Cargo cargo) throws ModelException {
		DBHandler db = new DBHandler();

		String sqlUpdate = "UPDATE cargos" + " SET nome= ? " + " WHERE cod = ?;";

		db.prepareStatement(sqlUpdate);

		db.setString(1, cargo.getNome());
		db.setInt(2, cargo.getId());
		System.out.println(sqlUpdate);
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Cargo cargo) throws ModelException {
		DBHandler db = new DBHandler();

		String sqlDelete = " DELETE FROM cargos " + " WHERE cod = ?;";

		db.prepareStatement(sqlDelete);
		db.setInt(1, cargo.getId());

		return db.executeUpdate() > 0;
	}

	@Override
	public List<Cargo> listAll() throws ModelException {
		DBHandler db = new DBHandler();

		List<Cargo> cargos = new ArrayList<Cargo>();

		// Declara um instrução SQL
		String sqlQuery = " SELECT * FROM cargos order by nome; ";

		db.createStatement();

		db.executeQuery(sqlQuery);

		while (db.next()) {
			Cargo c = createCargo(db);

			cargos.add(c);
		}

		return cargos;
	}

	@Override
	public Cargo findById(int id) throws ModelException {
		DBHandler db = new DBHandler();

		String sqlQuery = "SELECT * FROM cargos where cod = ?;";

		db.createStatement();
		db.prepareStatement(sqlQuery);
		db.setInt(1, id);
		System.out.println(sqlQuery);
		db.executeQuery();

		Cargo c = null;
		while (db.next()) {
			c = createCargo(db);
			break;
		}

		return c;

	}

	private Cargo createCargo(DBHandler db) throws ModelException {
		Cargo c = new Cargo();
		c.setId(db.getInt("cod"));
		c.setNome(db.getString("nome"));
		return c;
	}
}
