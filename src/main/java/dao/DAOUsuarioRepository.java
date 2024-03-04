package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection;

	public DAOUsuarioRepository() {
		connection = SingleConnection.getConnection();
	}

	/* RETORNA TODOS OS USUÁRIO CADASTRADOS NO BANCO EM FORMA DE LISTA */
	public List<ModelLogin> consultaUsuarioList(Long userLogado) throws Exception {

		List<ModelLogin> retorno = new ArrayList<ModelLogin>();

		String sql = "select * from model_login where useradmin is false and usuario_id = " + userLogado;
		PreparedStatement statment = connection.prepareStatement(sql);

		ResultSet resultado = statment.executeQuery();

		while (resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			// modelLogin.setSenha(resultado.getString("senha"));

			retorno.add(modelLogin);
		}
		return retorno;
	}

	/* MÉTODO PARA SAVAR USUÁRIO NO BANCO DE DADOS OU EDITAR SE JÁ EXISTE */
	public ModelLogin gravarUsuario(ModelLogin modelLogin, Long userLogado) throws Exception {

		if (modelLogin.isNovo()) { /* Grava um novo */

			String sql = "INSERT INTO model_login(login, senha, nome, email, usuario_id, perfil) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparasql = connection.prepareStatement(sql);

			preparasql.setString(1, modelLogin.getLogin());
			preparasql.setString(2, modelLogin.getSenha());
			preparasql.setString(3, modelLogin.getNome());
			preparasql.setString(4, modelLogin.getEmail());
			preparasql.setLong(5, userLogado);
			preparasql.setString(6, modelLogin.getPerfil());

			preparasql.execute();
			connection.commit();
		} else { /* Editar um existente */

			String sql = "update model_login set login=?, senha=?, nome=?, email=?, perfil=? where id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getSenha());
			statement.setString(3, modelLogin.getNome());
			statement.setString(4, modelLogin.getEmail());
			statement.setString(5, modelLogin.getPerfil());
			statement.setLong(6, modelLogin.getId());

			statement.executeUpdate();
			connection.commit();
		}
		return this.consultarUsuario(modelLogin.getLogin(), userLogado);
	}

	/* RETORNA POR NOME USUÁRIO CADASTRADO NO BANCO EM FORMA DE LISTA COM SUAS INFORMAÇÕES */
	public List<ModelLogin> consultaUsuarioList(String nome, Long userLogado) throws Exception {

		List<ModelLogin> retorno = new ArrayList<ModelLogin>();

		String sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = ?";
		PreparedStatement statment = connection.prepareStatement(sql);

		statment.setString(1, "%" + nome + "%");
		statment.setLong(2, userLogado);

		ResultSet resultado = statment.executeQuery();

		while (resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			// modelLogin.setSenha(resultado.getString("senha"));

			retorno.add(modelLogin);
		}
		return retorno;
	}
	
public ModelLogin consultaUsuarioLogado(String login) throws Exception  {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where upper(login) = upper('"+login+"')";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resutlado =  statement.executeQuery();
		
		while (resutlado.next()) /*Se tem resultado*/ {
			
			modelLogin.setId(resutlado.getLong("id"));
			modelLogin.setEmail(resutlado.getString("email"));
			modelLogin.setLogin(resutlado.getString("login"));
			modelLogin.setSenha(resutlado.getString("senha"));
			modelLogin.setNome(resutlado.getString("nome"));
			modelLogin.setUseradmin(resutlado.getBoolean("useradmin"));
			modelLogin.setPerfil(resutlado.getString("perfil"));
			
		}
		
		
		return modelLogin;
		
	}

	/* RETORNA USUÁRIO CADASTRADO NO BANCO DE DADOS */
	public ModelLogin consultarUsuario(String login, Long userLogado) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "select * from model_login where upper(login) = upper(?) and useradmin is false and usuario_id = " + userLogado;
		PreparedStatement statment = connection.prepareStatement(sql);
		statment.setString(1, login);

		ResultSet resultado = statment.executeQuery(); /*
														 * Executa a consulta SQL preparada e armazena o resultado em um
														 * objeto ResultSet chamado resultado.
														 */

		while (resultado.next()) { /* Se tem resultado */
			
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setUseradmin(resultado.getBoolean("useradmin"));		;

		}

		return modelLogin;
	}
	
	/* RETORNA USUÁRIO CADASTRADO NO BANCO DE DADOS */
	public ModelLogin consultarUsuario(String login) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "select * from model_login where upper(login) = upper(?) and useradmin is false";
		
		PreparedStatement statment = connection.prepareStatement(sql);

		ResultSet resultado = statment.executeQuery(); /*
														 * Executa a consulta SQL preparada e armazena o resultado em um
														 * objeto ResultSet chamado resultado.
														 */

		while (resultado.next()) { /* Se tem resultado */
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setUseradmin(resultado.getBoolean("useradmin"));
			modelLogin.setPerfil(resultado.getString("perfil"));

		}

		return modelLogin;
	}
	
	/*CONSULTADOR USUÁRIO PELO ID*/
	public ModelLogin consultaUsuarioID(String id, Long userLogado) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "select * from model_login where id = ? and useradmin is false and usuario_id = ?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));
		statement.setLong(2, userLogado);

		ResultSet resutlado = statement.executeQuery();

		while (resutlado.next()) /* Se tem resultado */ {

			modelLogin.setId(resutlado.getLong("id"));
			modelLogin.setEmail(resutlado.getString("email"));
			modelLogin.setLogin(resutlado.getString("login"));
			modelLogin.setSenha(resutlado.getString("senha"));
			modelLogin.setNome(resutlado.getString("nome"));
		}

		return modelLogin;

	}

	/* MÉTODO QUE VERIFICA SE O USUÁRIO JÁ EXISTE NO BANCO DE DADOS */
	public boolean validarLogin(String login) throws Exception {

		String sql = "select count(1) > 0 as quantidade from model_login where upper(login) = upper(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);

		ResultSet resultado = statement.executeQuery();

		resultado.next(); /* next serve para entrar nos resultados do sql */
		return resultado.getBoolean("quantidade");

	}

	/* MÉTODO PARA DELETAR USUÁRIO */
	public void deletarUser(String idUser) throws Exception {

		String sql = "delete from model_login where id = ? and useradmin is false";
		PreparedStatement preparaSql = connection.prepareStatement(sql);
		preparaSql.setLong(1, Long.parseLong(idUser));

		preparaSql.executeUpdate();

		connection.commit();
	}

}
