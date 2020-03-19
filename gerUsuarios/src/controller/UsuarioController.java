package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cargo;
import model.ModelException;
import model.Usuario;
import model.dao.DAOCargo;
import model.dao.DAOFactory;
import model.dao.DAOUsuario;
import model.dao.MySQLCargoDAO;

@WebServlet(urlPatterns = {"/usuarios", "/usuario/form", "/usuario/delete", "/usuario/insert", "/usuario/update"})
public class UsuarioController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI();
		
		switch (action) {
		case "/gerUsuarios/usuario/form": {
			listUsuarios(req);
			listCargos(req);
			req.setAttribute("action", "insert");
			ControllerUtil.forward(req, resp, "/form-usuario.jsp");
			break;
		}
		case "/gerUsuarios/usuario/update": {
			listUsuarios(req);
			listCargos(req);
			Usuario usuario = loadusuario(req);
			req.setAttribute("usuario", usuario);
			req.setAttribute("action", "update");
			ControllerUtil.forward(req, resp, "/form-usuario.jsp");
			break;
		}
		default:
			listUsuarios(req);
			
			ControllerUtil.transferSessionMessagesToRequest(req);
		
			ControllerUtil.forward(req, resp, "/usuarios.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI();
		
		if (action == null || action.equals("") ) {
			ControllerUtil.forward(req, resp, "/usuarios.jsp");
			return;
		}
		
		switch (action) {
		case "/gerUsuarios/usuario/delete":
			deleteusuario(req, resp);
			break;	
		case "/gerUsuarios/usuario/insert": {
			insertusuario(req, resp);
			break;
		}
		case "/gerUsuarios/usuario/update": {
			updateusuario(req, resp);
			break;
		}
		default:
			System.out.println("URL inválida " + action);
			break;
		}
			
		ControllerUtil.redirect(resp, req.getContextPath() + "/usuarios");
	}

	private Usuario loadusuario(HttpServletRequest req) {
		String usuarioCpf = req.getParameter("cpf");
		
		DAOUsuario dao = DAOFactory.createDAO(DAOUsuario.class);
		
		try {
			Usuario usuario = dao.findByCpf(usuarioCpf);
			
			if (usuario == null)
				throw new ModelException("Usuario não encontrado para alteração");
			
			return usuario;
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
		
		return null;
	}
	
	private void listUsuarios(HttpServletRequest req) {
		DAOUsuario dao = DAOFactory.createDAO(DAOUsuario.class);
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			usuarios = dao.listAll();
		} catch (ModelException e) {
			// Log no servidor
			e.printStackTrace();
		}
		
		if (usuarios != null)
			req.setAttribute("usuarios", usuarios);
	}
	
	private void listCargos(HttpServletRequest req) {
		DAOCargo dao = DAOFactory.createDAO(DAOCargo.class);
		
		List<Cargo> cargos = new ArrayList<Cargo>();
		try {
			cargos = dao.listAll();
		} catch (ModelException e) {
			// Log no servidor
			e.printStackTrace();
		}
		
		if (cargos != null)
			req.setAttribute("cargos", cargos);
	}
	private void insertusuario(HttpServletRequest req, HttpServletResponse resp) {
		MySQLCargoDAO daoc = new MySQLCargoDAO();
		String cpf = req.getParameter("cpf");
		String nome = req.getParameter("nome");
		String data_nascimento = req.getParameter("data_nascimento");
		String sexo = req.getParameter("sexo");
		Usuario usuario = new Usuario();
		try {
			Cargo cargo = daoc.findById(Integer.parseInt(req.getParameter("cargo")));
			usuario.setCargo(cargo);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ModelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		usuario.setCpf(cpf);
		usuario.setDataNascimento(data_nascimento);
		usuario.setNome(nome);
		usuario.setSexo(sexo);
		
		DAOUsuario dao = DAOFactory.createDAO(DAOUsuario.class);
		
		try {
			if (dao.save(usuario)) {
				ControllerUtil.sucessMessage(req, "usuario '" + usuario.getNome() + "' salvo com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "usuario '" + usuario.getNome() + "'  não pode ser salvo.");
			}
				
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}
	
	private void updateusuario(HttpServletRequest req, HttpServletResponse resp) {
		MySQLCargoDAO daoc = new MySQLCargoDAO();
		String cpf = req.getParameter("cpf");
		String nome = req.getParameter("nome");
		String data_nascimento = req.getParameter("data_nascimento");
		String sexo = req.getParameter("sexo");
		Usuario usuario = new Usuario();
		try {
			Cargo cargo = daoc.findById(Integer.parseInt(req.getParameter("cargo")));
			usuario.setCargo(cargo);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ModelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		usuario.setCpf(cpf);
		usuario.setDataNascimento(data_nascimento);
		usuario.setNome(nome);
		usuario.setSexo(sexo);
		
		DAOUsuario dao = DAOFactory.createDAO(DAOUsuario.class);
		
		try {
			if (dao.update(usuario)) {
				ControllerUtil.sucessMessage(req, "Usuário '" + usuario.getNome() + "' atualizado com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Usuário '" + usuario.getNome() + "' não pode ser atualizado.");
			}
				
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}
	
	private void deleteusuario(HttpServletRequest req, HttpServletResponse resp) {
		String cpf = req.getParameter("cpf");
				
		DAOUsuario dao = DAOFactory.createDAO(DAOUsuario.class);
		
		try {
			Usuario usuario = dao.findByCpf(cpf);
			
			if (usuario == null)
				throw new ModelException("Usuário nÃ£o encontrado para deleÃ§Ã£o.");
			
			if (dao.delete(usuario)) {
				ControllerUtil.sucessMessage(req, "Usuário '" + usuario.getNome() + "' deletado com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Usuário '" + usuario.getNome() + "' não pode ser deletado.");
			}
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}
}
