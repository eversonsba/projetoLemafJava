package model.dao;

import java.util.List;

import model.ModelException;
import model.Perfil;

public interface DAOPerfil {

	boolean save(Perfil perfil) throws ModelException ;
	boolean update(Perfil perfil) throws ModelException;
	boolean delete(Perfil perfil) throws ModelException;
	List<Perfil> listAll() throws ModelException;
	Perfil findById(Long id) throws ModelException;
}
