package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import dao.ReservaDAO;
import infra.ConnectionFactory;
import model.Reserva;

public class ReservaController {
	private ReservaDAO reservaDAO;
	
	public ReservaController() throws SQLException {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.reservaDAO = new ReservaDAO(connection);
	}
	
	public List<Reserva> buscar() throws SQLException {
		return this.reservaDAO.buscar();
	}
	
	public List<Reserva> buscarId(String id) throws SQLException {
		return this.reservaDAO.buscarId(id);
	}
	
	public void salvar(Reserva reserva) throws SQLException {
		this.reservaDAO.salvar(reserva);
	}
	
	public void atualizar(int id, Date dataEntrada, Date dataSaida, String valor, String formaPagamento) throws SQLException {
		this.reservaDAO.atualizar(id, dataEntrada, dataSaida, valor, formaPagamento);
	}
	
	public void deletar(int id) throws SQLException {
		this.reservaDAO.deletar(id);
	}
}
