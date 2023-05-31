package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import model.Reserva;

public class ReservaDAO {

	private Connection connection;

	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Reserva> buscar() throws SQLException {
		List<Reserva> reservas = new ArrayList<Reserva>();

		String getSQL = "select Id, DataEntrada, DataSaida, Valor, FormaPagamento from reservas";

		PreparedStatement pstm = connection.prepareStatement(getSQL); {
			pstm.execute();

			transformarResultEmReserva(reservas, pstm);
		}
		return reservas;
	}
	
	public List<Reserva> buscarId(String id) throws SQLException {
		List<Reserva> reservas = new ArrayList<Reserva>();

		String getByIdSQL = "select * from Reservas where Id = ?";

		PreparedStatement pstm = connection.prepareStatement(getByIdSQL); {
			pstm.setString(1, id);
			pstm.execute();

			transformarResultEmReserva(reservas, pstm);
		}
		return reservas;
	}
	
	private void transformarResultEmReserva(List<Reserva> reservas, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Reserva reserva = new Reserva(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getString(4),
						rst.getString(5));

				reservas.add(reserva);
			}
		}
	}
	
	public void salvar(Reserva reserva) throws SQLException {
		String postSQL = "insert into Reservas (DataEntrada, DataSaida, Valor, FormaPagamento) values (?, ?, ?, ?)";
		PreparedStatement pstm = connection.prepareStatement(postSQL, Statement.RETURN_GENERATED_KEYS); {
			pstm.setDate(1, reserva.getDataEntrada());
			pstm.setDate(2, reserva.getDataSaida());
			pstm.setString(3, reserva.getValor());
			pstm.setString(4, reserva.getFormaPagamento());
			
			pstm.executeUpdate();
			
			ResultSet rst = pstm.getGeneratedKeys(); {
				while (rst.next()) {
					reserva.setId(rst.getInt(1));
				}
			}
		}
	}
	
	public void atualizar(int id, Date dataEntrada, Date dataSaida, String valor, String formaPagamento) throws SQLException {
		String putSQL = "update Reservas set DataEntrada = ?, DataSaida = ?, Valor = ?, FormaPagamento = ? where id = ?";
		PreparedStatement pstm = connection.prepareStatement(putSQL); {
			pstm.setInt(5, id);
			pstm.setDate(1, dataEntrada);
			pstm.setDate(2, dataSaida);
			pstm.setString(3, valor);
			pstm.setString(4, formaPagamento);
			
			pstm.executeUpdate();
		}
	}
	
	public void deletar(int id) throws SQLException {
		String deleteSQL = "delete from reservas where id = ?";
		PreparedStatement pstm = connection.prepareStatement(deleteSQL); {
			pstm.setInt(1, id);
			
			pstm.executeUpdate();
		}
	}
}
