package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnection;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/principal/*" }) /* Intercepta todas as requisições que vierem do projeto ou mapeamento */
public class FilterAutenticacao extends HttpFilter implements Filter {

	/* Atributo de CONEXÃO COM O BANCO */
	private static Connection connection;

	public FilterAutenticacao() {
	}

	/* Encerra os processos quando o servidor é parado */
	/* Exemplo: Destroi os processos de conexão com o banco de dados */
	public void destroy() {

		/* ENCERRA A CONEXÃO COM O BANCO QUANDO PARAR O SERVIDOR */
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* Intercepta as requisições e da as respostas no sistema */
	/* Tudo o que for feito no sistema vai passar por aqui */
	/*
	 * Exemplo: Validação de autenticação; Dar commit e rolback de transações no
	 * banco
	 */
	/* Validar e fazer redirecionamento de páginas */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			String usuarioLogado = (String) session.getAttribute("usuario");

			String urlParaAutenticar = req.getServletPath(); /* Url que está sendo acessada */

			/* Validar se está logado senão redireciona para a tela de login */
			if (usuarioLogado == null
					&& !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin1")) { /* Não está logado */

				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor realizar o login!");
				redireciona.forward(request, response);
				return; /* Para a execução e redireciona para o login */

			} else {
				chain.doFilter(request, response);
			}

			connection.commit();/* Deu tudo certo, então commita as alterações no banco */

		} catch (Exception e) {
			e.printStackTrace();

			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	/* Inicia os processos ou recursos quando o servidor sobe o projeto */
	/* Exemplo: iniciar a conexão com o banco de dados */
	public void init(FilterConfig fConfig) throws ServletException {

		/* INICIA A CONEXÃO COM O BANCO QUANDO SOBE O PROJETO */
		connection = SingleConnection.getConnection();

	}

}
