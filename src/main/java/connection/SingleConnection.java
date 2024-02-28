package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	private static String url = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String password = "05othon0995";
	private static String user = "postgres";
	private static Connection connection = null;

	static { 
		conectar();
	}

	public SingleConnection() { /*Quando tiver uma instancia vai conectar*/
		conectar();
	}

	private static void conectar() {
		try {

			if (connection == null) {
				Class.forName("org.postgresql.Driver"); /*Carrega o drive de conexão do banco*/
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false); /*Para não efetuar alterações no bacno sem nosso comando*/
				System.out.println("Conectou com sucesso!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}

}

/*Para fazer o teste de conexão, chama o método no filter*/