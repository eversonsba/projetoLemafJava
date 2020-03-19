package model.dao;

import java.util.List;

import model.Cargo;
import model.ModelException;
import model.Usuario;

public interface DAOUsuario {

	boolean save(Usuario usuario) throws ModelException ;
	boolean update(Usuario usuario) throws ModelException;
	boolean delete(Usuario usuario) throws ModelException;
	List<Usuario> listAll() throws ModelException;
	Usuario findByCpf(String cnpj) throws ModelException;
}
