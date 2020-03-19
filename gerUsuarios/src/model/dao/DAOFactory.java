package model.dao;

import java.util.HashMap;
import java.util.Map;

public class DAOFactory {
private static Map<Class, Object> listDAOsInterfaces = new HashMap<Class, Object>();
	
	// Para o DAOFactory funcionar para suas classes de domínio, adicione na 
	// lista suas interfaces e classes DAO na listDAOsInterfaces
	static {
		listDAOsInterfaces.put(DAOCargo.class, new MySQLCargoDAO());
		listDAOsInterfaces.put(DAOUsuario.class, new MySQLUsuarioDAO());
		listDAOsInterfaces.put(DAOPerfil.class, new MySQLPerfilDAO());
		
	}
	
	@SuppressWarnings("unchecked")
	public static <DAOInterface> DAOInterface createDAO(Class<?> entity){
		return (DAOInterface) listDAOsInterfaces.get(entity);
	} 

}
