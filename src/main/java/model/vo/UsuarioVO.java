package model.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UsuarioVO {
	
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	private int idUsuario;
	private TipoUsuarioVO tipoUsuario;
	private String nome;
	private String cpf;
	private String email;
	private LocalDate dataCadastro;
	private LocalDate dataExpiracao;
	private String login;
	private String senha;
	
	public UsuarioVO(int idUsuario, TipoUsuarioVO tipoUsuario, String nome, String cpf, String email,
			LocalDate dataCadastro, LocalDate dataExpiracao, String login, String senha) {
		super();
		this.idUsuario = idUsuario;
		this.tipoUsuario = tipoUsuario;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.dataCadastro = dataCadastro;
		this.dataExpiracao = dataExpiracao;
		this.login = login;
		this.senha = senha;
	}

	public UsuarioVO() {
		super();
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public TipoUsuarioVO getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuarioVO tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDate getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(LocalDate dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void imprimir() {
		System.out.printf("\n%3s %-13s %-20s %-11s %-20s %-15s %-15s %-10s %-10s  ",
		this.getIdUsuario(),
		this.getTipoUsuario(),
		this.getNome(),
		this.getCpf(),
		this.getEmail(),
		this.validarData(this.getDataCadastro()),
		this.validarData(this.getDataExpiracao()),
		this.getLogin(),
		this.getSenha());
	}

	private String validarData(LocalDate data) {
		String resultado = "";
		if(data != null) {
			resultado = data.format(dataFormatter);
		}
		return resultado;
		
	}
	
	
	
	
}
