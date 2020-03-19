package testes;

import model.ModelException;
import model.Perfil;
import model.dao.MySQLCargoDAO;
import model.dao.MySQLPerfilDAO;
import model.dao.MySQLUsuarioDAO;

public class main {

	public static void main(String[] args) throws ModelException {
		
		MySQLCargoDAO daou = new MySQLCargoDAO();
		
		daou.delete(daou.findById(1));
	}
	

}
