package servlet;

import java.io.IOException;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	public ServletUsuarioController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {

				String idUser = request.getParameter("id");
				daoUsuarioRepository.deletarUser(idUser);
				request.setAttribute("msg", "Excluido com sucesso!");
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarAjax")) { // C Ajax

				String idUser = request.getParameter("id");
				daoUsuarioRepository.deletarUser(idUser);
				response.getWriter().write("Excluido com sucesso!");

			} else {
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);

			/* VERIFICANDO SE USUÁRIO JÁ EXISTE COM O MÉTODO VALIDARLOGIN SENÃO SALVA */
			if (daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				request.setAttribute("msg", "Usuário já existe.");
			} else {
				if (modelLogin.isNovo()) {
					/* Mensagem de confirmação do salvamento para novo usuário */
					request.setAttribute("msg", "Operação de SALVAR usuário realizada com sucesso!");
				} else {
					request.setAttribute("msg", "Operação de EDITAR usuário realizada com sucesso!");
				}
				/* SALVANDO USUÁRIO NO BANCO DE DADOS */
				modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin);
			}

			/* Para manter os dados na tela após salvar o usuário */
			request.setAttribute("modelLogin", modelLogin);

			/* Redirecionando para pagina */
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
			redirecionar.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

}
