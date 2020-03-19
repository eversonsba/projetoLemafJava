package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.Cargo;
import model.ModelException;
import model.dao.DAOCargo;
import model.dao.DAOFactory;

@WebServlet(urlPatterns = {"/cargos", "/cargo/form", "/cargo/delete", "/cargo/insert", "/cargo/update"})
public class CargoController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI();
		
		switch (action) {
		case "/gerUsuarios/cargo/form": {
			listCargos(req);
			req.setAttribute("action", "insert");
			ControllerUtil.forward(req, resp, "/form-cargo.jsp");
			break;
		}
		case "/gerUsuarios/cargo/update": {
			listCargos(req);
			Cargo cargo = loadCargo(req);
			req.setAttribute("cargo", cargo);
			req.setAttribute("action", "update");
			ControllerUtil.forward(req, resp, "/form-cargo.jsp");
			break;
		}
		default:
			listCargos(req);
			
			ControllerUtil.transferSessionMessagesToRequest(req);
		
			ControllerUtil.forward(req, resp, "/cargos.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI();
		
		if (action == null || action.equals("") ) {
			ControllerUtil.forward(req, resp, "/cargos.jsp");
			return;
		}
		
		switch (action) {
		case "/gerUsuarios/cargo/delete":
			deleteCargo(req, resp);
			break;	
		case "/gerUsuarios/cargo/insert": {
			insertCargo(req, resp);
			break;
		}
		case "/gerUsuarios/cargo/update": {
			updateCargo(req, resp);
			break;
		}
		default:
			System.out.println("URL inválida " + action);
			break;
		}
			
		ControllerUtil.redirect(resp, req.getContextPath() + "/cargos");
	}

	private Cargo loadCargo(HttpServletRequest req) {
		int cargoid = Integer.parseInt(req.getParameter("id"));
			
		DAOCargo dao = DAOFactory.createDAO(DAOCargo.class);
		
		try {
			Cargo cargo = dao.findById(cargoid);
			
			if (cargo == null)
				throw new ModelException("Cargo não encontrado para alteração");
			
			return cargo;
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
		
		return null;
	}
	
	private void listCargos(HttpServletRequest req) {
		DAOCargo dao = DAOFactory.createDAO(DAOCargo.class);
		
		List<Cargo> cargos = null;
		try {
			cargos = dao.listAll();
		} catch (ModelException e) {
			// Log no servidor
			e.printStackTrace();
		}
		
		if (cargos != null)
			req.setAttribute("cargos", cargos);
	}
	
	private void insertCargo(HttpServletRequest req, HttpServletResponse resp) {
		String nome = req.getParameter("nome");
		
		Cargo cargo = new Cargo();
		
		cargo.setNome(nome);
		
		DAOCargo dao = DAOFactory.createDAO(DAOCargo.class);
		
		try {
			if (dao.save(cargo)) {
				ControllerUtil.sucessMessage(req, "Cargo '" + cargo.getNome() + "' salvo com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Cargo '" + cargo.getNome() + "'  não pode ser salvo.");
			}
				
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}
	
	private void updateCargo(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		String nome = req.getParameter("nome");
		
		Cargo cargo = new Cargo();
		
		cargo.setId(id);
		cargo.setNome(nome);
		
		DAOCargo dao = DAOFactory.createDAO(DAOCargo.class);
		
		try {
			if (dao.update(cargo)) {
				ControllerUtil.sucessMessage(req, "Usuário '" + cargo.getNome() + "' atualizado com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Usuário '" + cargo.getNome() + "' não pode ser atualizado.");
			}
				
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}
	
	private void deleteCargo(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
				
		DAOCargo dao = DAOFactory.createDAO(DAOCargo.class);
		
		try {
			Cargo cargo = dao.findById(id);
			
			if (cargo == null)
				throw new ModelException("Usuário nÃ£o encontrado para deleÃ§Ã£o.");
			
			if (dao.delete(cargo)) {
				ControllerUtil.sucessMessage(req, "Usuário '" + cargo.getNome() + "' deletado com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Usuário '" + cargo.getNome() + "' não pode ser deletado.");
			}
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}
}
