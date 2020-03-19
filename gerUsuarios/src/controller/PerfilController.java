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
import model.Perfil;
import model.Usuario;
import model.dao.DAOCargo;
import model.dao.DAOFactory;
import model.dao.DAOPerfil;
import model.dao.DAOUsuario;
import model.dao.MySQLCargoDAO;
import model.dao.MySQLPerfilDAO;
import model.dao.MySQLUsuarioDAO;

@WebServlet(urlPatterns = { "/perfis", "/perfil/form", "/perfil/delete", "/perfil/insert", "/perfil/update" })
public class PerfilController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI();

		switch (action) {
		case "/gerUsuarios/perfil/form": {
			listUsuarios(req);
			listPerfis(req);
			req.setAttribute("action", "insert");
			ControllerUtil.forward(req, resp, "/form-perfil.jsp");
			break;
		}
		case "/gerUsuarios/perfil/update": {
			listUsuarios(req);
			listPerfis(req);
			Perfil perfil = loadperfil(req);
			req.setAttribute("perfil", perfil);
			req.setAttribute("action", "update");
			ControllerUtil.forward(req, resp, "/form-perfil.jsp");
			break;
		}
		default:
			listPerfis(req);

			ControllerUtil.transferSessionMessagesToRequest(req);

			ControllerUtil.forward(req, resp, "/perfis.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI();

		if (action == null || action.equals("")) {
			ControllerUtil.forward(req, resp, "/perfis.jsp");
			return;
		}

		switch (action) {
		case "/gerUsuarios/perfil/delete":
			deleteperfil(req, resp);
			break;
		case "/gerUsuarios/perfil/insert": {
			insertperfil(req, resp);
			break;
		}
		case "/gerUsuarios/perfil/update": {
			updateperfil(req, resp);
			break;
		}
		default:
			System.out.println("URL inválida " + action);
			break;
		}

		ControllerUtil.redirect(resp, req.getContextPath() + "/perfis");
	}

	private Perfil loadperfil(HttpServletRequest req) {
		Long perfilId = Long.parseLong(req.getParameter("id"));

		DAOPerfil dao = DAOFactory.createDAO(DAOPerfil.class);

		try {
			Perfil perfil = dao.findById(perfilId);

			if (perfil == null)
				throw new ModelException("Perfil não encontrado para alteração");

			return perfil;
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

	private void listPerfis(HttpServletRequest req) {
		DAOPerfil dao = DAOFactory.createDAO(DAOPerfil.class);

		List<Perfil> perfis = new ArrayList<Perfil>();
		try {
			perfis = dao.listAll();
		} catch (ModelException e) {
			// Log no servidor
			e.printStackTrace();
		}

		if (perfis != null)
			req.setAttribute("perfis", perfis);
	}

	private void insertperfil(HttpServletRequest req, HttpServletResponse resp) {
		MySQLPerfilDAO daop = new MySQLPerfilDAO();
		Long id = Long.parseLong(req.getParameter("usuario"));
		String nome = req.getParameter("nome");

		MySQLUsuarioDAO daou = new MySQLUsuarioDAO();

		Usuario usuario;
		Perfil perfil = new Perfil();
		try {
			usuario = daou.findByCpf(req.getParameter("usuario"));
			perfil.setUsuario(usuario);
		} catch (ModelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		perfil.setNome(nome);

		try {
			if (daop.save(perfil)) {
				ControllerUtil.sucessMessage(req, "perfil '" + perfil.getNome() + "' salvo com sucesso.");
			} else {
				ControllerUtil.errorMessage(req, "perfil '" + perfil.getNome() + "'  não pode ser salvo.");
			}

		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}

	private void updateperfil(HttpServletRequest req, HttpServletResponse resp) {
		MySQLPerfilDAO daop = new MySQLPerfilDAO();
		Long id = Long.parseLong(req.getParameter("id"));
		String nome = req.getParameter("nome");

		MySQLUsuarioDAO daou = new MySQLUsuarioDAO();

		Usuario usuario;
		Perfil perfil = new Perfil();
		try {
			usuario = daou.findByCpf(req.getParameter("usuario"));
			perfil.setUsuario(usuario);
			perfil.setId(id);
		} catch (ModelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		perfil.setNome(nome);

		try {
			if (daop.update(perfil)) {
				ControllerUtil.sucessMessage(req, "perfil '" + perfil.getNome() + "' salvo com sucesso.");
			} else {
				ControllerUtil.errorMessage(req, "perfil '" + perfil.getNome() + "'  não pode ser salvo.");
			}

		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}

	private void deleteperfil(HttpServletRequest req, HttpServletResponse resp) {
		Long id = Long.parseLong(req.getParameter("id"));

		DAOPerfil dao = DAOFactory.createDAO(DAOPerfil.class);

		try {
			Perfil perfil = dao.findById(id);

			if (perfil == null)
				throw new ModelException("Perfil nÃ£o encontrado para deleÃ§Ã£o.");

			if (dao.delete(perfil)) {
				ControllerUtil.sucessMessage(req, "Usuário '" + perfil.getNome() + "' deletado com sucesso.");
			} else {
				ControllerUtil.errorMessage(req, "Usuário '" + perfil.getNome() + "' não pode ser deletado.");
			}
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}
}
