package servlet;

import java.io.IOException;

import dao.DAOLoginRepository;
import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

/*O chamado Controller são as servelets (ServletLoginController)*/
//@WebServlet (urlPatterns = {"/principal/ServletLogin1", "/ServletLogin1" })
public class ServletLogin1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	public ServletLogin1() {

	}

	/* Recebe os dados pela URL em parâmetros */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*LOGOUT DO SISTEMA*/
		String acao = request.getParameter("acao");
		
		if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate(); //invalida a sessão
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			redirecionar.forward(request, response);
		} else {
			doPost(request, response); /*Se acao = null chama dopost*/
		}
		
		
	}

	/* Recebe os dados enviados por um formulário */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");

		/* Todos os dados vindo da tela devem ser guardados em um objeto */
		/* Portanto cria-se um objeto no pacote model para receber os parâmetros */

		try {
			if (login != null && !login.isEmpty() && senha != null
					&& !senha.isEmpty()) { /* Validando se os campos não estão vazio */
				ModelLogin modelLogin = new ModelLogin();
				modelLogin.setLogin(login);
				modelLogin.setSenha(senha);

				if (daoLoginRepository.validarAutenticacao(modelLogin)) { /*Simula login*/
					
					modelLogin = daoUsuarioRepository.consultaUsuarioLogado(login);
					
					request.getSession().setAttribute("usuario", modelLogin.getLogin());
					request.getSession().setAttribute("isAdmin", modelLogin.getUseradmin());

					if (url == null || url.equals("null")) {
						url = "principal/principal.jsp";
					}

					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);

				} else {
					RequestDispatcher redirecionar = request
							.getRequestDispatcher("index.jsp"); /*
																 * Caso os campos não estejam preenchidos o dispatcher
																 * redireciona para tela index
																 */
					request.setAttribute("msg", "Login ou Senha inválidos!");
					redirecionar.forward(request, response);
				}

			} else {
				RequestDispatcher redirecionar = request
						.getRequestDispatcher("index.jsp"); /*
															 * Caso os campos não estejam preenchidos o dispatcher
															 * redireciona para tela index
															 */
				request.setAttribute("msg", "Login ou Senha inválidos!");
				redirecionar.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request
					.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

}
