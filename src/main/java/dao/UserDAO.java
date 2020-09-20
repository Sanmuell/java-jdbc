package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.UserModel;


public class UserDAO {

	private Connection conexao;

	// Injeta para dentro do objeto "conexao" a "connectionFactory" 
	public UserDAO() {
		conexao = SingleConnection.buscarConexao();
	}
	
	
	public void salvar (UserModel usermodel) {

		try {

			String sql = "insert into userposjava( nome, email) values(?,?) ";
			java.sql.PreparedStatement inserir = conexao.prepareStatement(sql);
			inserir.setString(1, usermodel.getNome());
			inserir.setString(2, usermodel.getEmail());
			inserir.execute();
			conexao.commit();

		} catch (Exception e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}
	
	
// Retorna todos os objetos 
	public List<UserModel> listar() throws Exception {
		List<UserModel> lista = new ArrayList<UserModel>();

		String sql = "select * from userposjava ";

		java.sql.PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery(); 

		while (resultado.next()) { // enquanto tiver resultado
			
			UserModel usermodel = new UserModel(); //instancia novos objetos
			
			usermodel.setId(resultado.getLong("id"));
			usermodel.setNome(resultado.getString("nome"));
			usermodel.setEmail(resultado.getString("email"));

			lista.add(usermodel);
		}

		return lista;

	}

	public UserModel buscar(Long id) throws Exception {
		UserModel retorno = new UserModel();

		String sql = "select * from userposjava where id =" + id;

		java.sql.PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) { // retornar apenas um ou nenhum
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));

		}

		return retorno;

	}

	public List<BeanUserFone> listaUserFone(Long idUser) {
		List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();
		
		
		String sql = "select nome, numero, email from telefoneuser as fone";
		sql += " inner join userposjava as userp";
		sql += " on fone.usuariopessoa = userp.id";
		sql += " where userp.id = " + idUser;

	

		try {
			java.sql.PreparedStatement statement = conexao.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				BeanUserFone userFone = new BeanUserFone();
				userFone.setEmail(resultSet.getString("email"));
				userFone.setNome(resultSet.getString("nome"));
				userFone.setNumero(resultSet.getString("numero"));
				beanUserFones.add(userFone);
				
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return beanUserFones;
	}

	public void atualizar(UserModel usermodel) {

		try {

			String sql = "update userposjava set nome = ? where id =" + usermodel.getId();
			java.sql.PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, usermodel.getNome());

			statement.execute();
			conexao.commit();

		} catch (Exception e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void deletar(Long id) {
		try {

			String sql = "delete from userposjava where id = " + id;
			java.sql.PreparedStatement preparedStatement = conexao.prepareStatement(sql);
			preparedStatement.execute();
			conexao.commit();

		} catch (Exception e) {
			try {

			} catch (Exception e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		}
	}

	public void salvarTelefone(Telefone telefone) {

		try {

			String sql = "INSERT INTO telefoneuser(numero, tipo, usuariopessoa) values (?,?,?)";
			java.sql.PreparedStatement statement = conexao.prepareStatement(sql);

			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getUsuario());
			statement.execute();
			conexao.commit();

		} catch (Exception e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}

	}

}
