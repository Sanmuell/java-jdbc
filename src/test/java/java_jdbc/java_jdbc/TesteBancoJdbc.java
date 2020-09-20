package java_jdbc.java_jdbc;

import java.util.List;

import org.junit.Test;

import dao.UserDAO;
import model.BeanUserFone;
import model.Telefone;
import model.UserModel;

public class TesteBancoJdbc {

	@Test
	public void salvar() {

		UserDAO UserDAO = new UserDAO(); // instância do objeto DAO
		UserModel usermodel = new UserModel(); // instância do objeto Modelo

		//setando dados no objeto
		usermodel.setNome("Kaio");
		usermodel.setEmail("kaio@gmail.com");

		UserDAO.salvar(usermodel);

	}
	
	
	

	@Test
	public void listarTodos() {
		UserDAO dao = new UserDAO();
		try {
			List<UserModel> list = dao.listar();

			for (UserModel usermodel : list) {
				System.out.println(usermodel);
				System.out.println("------------------------");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Test
	public void buscar() {
		UserDAO dao = new UserDAO();
		try {
			UserModel usermodel = dao.buscar(6L);

			System.out.println(usermodel);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void atualizar() {

		try {
			UserDAO dao = new UserDAO();

			UserModel objetoBanco = dao.buscar(5L);

			objetoBanco.setNome("Nome mudado com metodo atualizar");
			
			dao.atualizar(objetoBanco);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	 @Test
	public void deletar() {
		try {
			
			UserDAO dao = new UserDAO();
			dao.deletar(3L);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	 @Test
	 public void inserirTelefone() {
		 Telefone telefone = new Telefone();
		 telefone.setNumero("(11) 323423423");
		 telefone.setTipo("Casa");
		 telefone.setUsuario(1L);
		 
		 UserDAO dao = new UserDAO();
		 dao.salvarTelefone(telefone);
		 
	 }
	 
	 @Test
	 public void carregaFonesUser () {
		 UserDAO dao = new UserDAO();
		 
		 List<BeanUserFone> beanUserFones = dao.listaUserFone(2L);
		 
		 for (BeanUserFone beanUserFone : beanUserFones) {
			 System.out.println(beanUserFone);
			 System.out.println("-----------------------");
			
		}
	 }
	
}
