package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import model.Cargo;
import model.ModelException;

public class MySQLUsuarioDAO implements DAOUsuario {
	
	MySQLCargoDAO daoc = new MySQLCargoDAO();

	@Override
	public boolean save(Usuario usuario) throws ModelException {
		DBHandler db = new DBHandler();

		String sqlInsert = "insert into usuarios value(?,?,?,?,?,now());";
		
		db.prepareStatement(sqlInsert);
		db.setString(1, usuario.getNome());
		db.setString(2, usuario.getCpf());
		db.setString(3, usuario.getDataNascimento());
		db.setString(4, usuario.getSexo());
		db.setLong(5, usuario.getCargo().getId());
		System.out.println(usuario.getDataNascimento());
		System.out.println(sqlInsert);
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean update(Usuario usuario) throws ModelException {
		DBHandler db = new DBHandler();

		String sqlUpdate = "UPDATE usuarios  SET nome= ?, data_nacimento = ?, sexo = ? , cod_cargo = ?  WHERE cpf = ?;";

		db.prepareStatement(sqlUpdate);

		db.setString(1, usuario.getNome());
		db.setString(2, usuario.getDataNascimento());
		db.setString(3, usuario.getSexo());
		db.setLong(4, usuario.getCargo().getId());
		db.setString(5, usuario.getCpf());

		System.out.println(sqlUpdate);
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Usuario usuario) throws ModelException {
		DBHandler db = new DBHandler();

		String sqlDelete = " DELETE FROM usuarios " + " WHERE cpf = ?;";

		db.prepareStatement(sqlDelete);
		db.setString(1, usuario.getCpf());

		return db.executeUpdate() > 0;
	}

	@Override
	public List<Usuario> listAll() throws ModelException {
		DBHandler db = new DBHandler();

		List<Usuario> usuarios = new ArrayList<Usuario>();

		// Declara um instrução SQL
		String sqlQuery = " SELECT * FROM usuarios order by nome; ";

		db.createStatement();

		db.executeQuery(sqlQuery);

		while (db.next()) {
			Usuario u = createUsuario(db);

			usuarios.add(u);
		}

		return usuarios;
	}

	private Usuario createUsuario(DBHandler db) throws ModelException {
		Usuario u = new Usuario();
		u.setNome(db.getString("nome"));
		u.setCpf(db.getString("cpf"));
		u.setSexo(db.getString("sexo"));
		u.setDataNascimento(db.getString("data_nacimento"));
		u.setData_cadastro(db.getString("data_cadastro"));
		u.setCargo(daoc.findById(db.getInt("cod_cargo")));
		System.out.println(u.getCargo().getId());
		return u;
	}

	@Override
	public Usuario findByCpf(String cpf) throws ModelException {
		DBHandler db = new DBHandler();

		String sqlQuery = " SELECT * FROM usuarios where cpf = ?; ";

		db.prepareStatement(sqlQuery);
		db.setString(1, cpf);
		System.out.println(sqlQuery);
		db.executeQuery();

		Usuario u = null;
		while (db.next()) {
			u = createUsuario(db);
			break;
		}

		return u;

	}

}
