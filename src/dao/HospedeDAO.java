package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import model.Hospede;

public class HospedeDAO {

	private Connection connection;

	public HospedeDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Hospede> buscar() throws SQLException {
		List<Hospede> hospedes = new ArrayList<Hospede>();

		String getSQL = "Select id, Nome, Sobrenome, DataNascimento, Telefone, IdReserva, nacionalidade from hospedes";

		PreparedStatement pstm = connection.prepareStatement(getSQL);
		{
			pstm.execute();

			transformarResultEmHospede(hospedes, pstm);
		}
		return hospedes;
	}

	public List<Hospede> buscarSobrenome(String sobrenome) throws SQLException {
		List<Hospede> hospedes = new ArrayList<Hospede>();

		String getByIdSQL = "Select * from hospedes where sobrenome = ?";

		PreparedStatement pstm = connection.prepareStatement(getByIdSQL);
		{
			pstm.setString(1, sobrenome);
			pstm.execute();

			transformarResultEmHospede(hospedes, pstm);
		}
		return hospedes;
	}

	private void transformarResultEmHospede(List<Hospede> hospedes, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Hospede hospede = new Hospede(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDate(4),
						rst.getString(5), rst.getInt(6), rst.getString(7));

				hospedes.add(hospede);
			}
		}
	}

	public void salvar(Hospede hospede) throws SQLException {
		String postSQL = "insert into hospedes (nome, sobrenome, DataNascimento, nacionalidade, telefone, idReserva) values (?, ?, ?, ?, ?, ?)";
		PreparedStatement pstm = connection.prepareStatement(postSQL, Statement.RETURN_GENERATED_KEYS);
		{
			pstm.setString(1, hospede.getNome());
			pstm.setString(2, hospede.getSobrenome());
			pstm.setDate(3, hospede.getDataNascimento());
			pstm.setString(4, hospede.getNacionalidade());
			pstm.setString(5, hospede.getTelefone());
			pstm.setInt(6, hospede.getIdReserva());

			pstm.executeUpdate();

			ResultSet rst = pstm.getGeneratedKeys();
			{
				while (rst.next()) {
					hospede.setId(rst.getInt(1));
				}
			}
		}
	}

	public void atualizar(int id, String nome, String sobrenome,  String nacionalidade, String telefone) throws SQLException {
		String putSQL = "update Hospedes set nome = ?, sobrenome = ?, nacionalidade = ?, telefone = ? where id = ?";
		PreparedStatement pstm = connection.prepareStatement(putSQL);
		{
			pstm.setInt(5, id);
			pstm.setString(1, nome);
			pstm.setString(2, sobrenome);
			pstm.setString(3, nacionalidade);
			pstm.setString(4, telefone);

			pstm.executeUpdate();
		}
	}
	
	public void deletar(int id) throws SQLException {
		String deleteSQL = "delete from hospedes where id = ?";
		PreparedStatement pstm = connection.prepareStatement(deleteSQL);
		{
			pstm.setInt(1, id);
			
			pstm.executeUpdate();
		}
	}
}
