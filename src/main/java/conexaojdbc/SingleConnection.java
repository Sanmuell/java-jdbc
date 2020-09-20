package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	
	// DADOS DO BANCO 
	private static String url = "jdbc:mysql://localhost:3306/java-jdbc?useTimezone=true&serverTimezone=UTC";
	private static String password = "root";
	private static String user = "root";
	private static Connection conexao = null;

	// metodo que retorna o objeto conexão
	public static Connection buscarConexao() {
		return conexao;
	}

	// De qualquer lugar que eu chamar o SingleConnection le irá chamar o método
	// Conectar automaticamente
	static {
		conectar();

	}

	// construtor que chama método conectar
	public SingleConnection() {
		conectar();
	}


	private static void conectar() {
		try {

			if (conexao == null) { 
				conexao = DriverManager.getConnection(url, user, password);
				conexao.setAutoCommit(false); // para não salvar automaticamente
				System.out.println("Conectado com sucesso");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}