package model;

import java.sql.Date;

public class Hospede {
	private int id;
	private String nome;
	private String sobrenome;
	private Date dataNascimento;
	private String nacionalidade;
	private String telefone;
	private int idReserva;
	
	public Hospede(int id, String nome, String sobrenome, Date dataNascimento, String telefone, int idReserva, String nacionalidade){
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.idReserva = idReserva;
		this.nacionalidade = nacionalidade;
	}
	
	public Hospede(String nome, String sobrenome, Date dataNascimento, String telefone, String nacionalidade, int idReserva) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.idReserva = idReserva;
		this.nacionalidade = nacionalidade;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public int getIdReserva() {
		return idReserva;
	}
}
