package model;

import java.math.BigDecimal;
import java.sql.Date;

public class Reserva {
	
	private int id;
	private Date dataEntrada;
	private Date dataSaida;
	private String valor;
	private String formaPagamento;
	
	public Reserva(int id, Date dataEntrada, Date dataSaida, String valor, String formaPagamento) {
		this.id = id;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
	}
	
	public Reserva(Date dataEntrada, Date dataSaida, String valor, String formaPagamento) {
		super();
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
		
	}

	public int getId() { return id; }
	
	public void setId(int id) { this.id = id; }
	
	public Date getDataEntrada() { return dataEntrada; }
	
	public void setDataEntrada(Date dataEntrada) { this.dataEntrada = dataEntrada; }
	
	public Date getDataSaida() { return dataSaida; }
	
	public void setDataSaida(Date dataSaida) { this.dataSaida = dataSaida; }
	
	public String getValor() { return valor; }
	
	public void setValor(String valor) { this.valor = valor; }
	
	public String getFormaPagamento() { return formaPagamento; }
	
	public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }
	
}

