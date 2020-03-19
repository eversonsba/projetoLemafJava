package model.dao;

import java.util.List;

import model.Cargo;
import model.ModelException;

public interface DAOCargo {

	boolean save(Cargo cargo) throws ModelException ;
	boolean update(Cargo cargo) throws ModelException;
	boolean delete(Cargo cargo) throws ModelException;
	List<Cargo> listAll() throws ModelException;
	Cargo findById(int cargoid) throws ModelException;
}
