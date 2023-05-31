package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.HospedeDAO;
import infra.ConnectionFactory;
import model.Hospede;

public class HospedeController {
	private HospedeDAO hospedeDAO;
	
	public HospedeController() throws SQLException {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.hospedeDAO = new HospedeDAO(connection);
	}
	
	public List<Hospede> buscar() throws SQLException {
		return this.hospedeDAO.buscar();
	}
	
	public List<Hospede> buscarSobrenome(String sobrenome) throws SQLException {
		return this.hospedeDAO.buscarSobrenome(sobrenome);
	}
	
	public void salvar(Hospede hospede) throws SQLException {
		this.hospedeDAO.salvar(hospede);
	}
	
	public void atualizar(int id ,String nome, String sobrenome, String nacionalidade, String telefone) throws SQLException {
		this.hospedeDAO.atualizar(id, nome, sobrenome, nacionalidade, telefone);
	}
	
	public void deletar(int id) throws SQLException {
		this.hospedeDAO.deletar(id);
	}
}
