package model.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ChamadoVO {
	
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	private int idChamado;
	private int idUsuario;
	private int idTecnico;
	private String titulo;
	private String descricao;
	private LocalDate dataAbertura;
	private LocalDate dataFechamento;
	private String solucao;
	
	public ChamadoVO(DateTimeFormatter dataFormatter, int idChamado, int idUsuario, int idTecnico, String titulo,
			String descricao, LocalDate dataAbertura, LocalDate dataFechamento, String solucao) {
		super();
		this.dataFormatter = dataFormatter;
		this.idChamado = idChamado;
		this.idUsuario = idUsuario;
		this.idTecnico = idTecnico;
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataAbertura = dataAbertura;
		this.dataFechamento = dataFechamento;
		this.solucao = solucao;
	}

	public ChamadoVO() {
		super();
	}

	public DateTimeFormatter getDataFormatter() {
		return dataFormatter;
	}

	public void setDataFormatter(DateTimeFormatter dataFormatter) {
		this.dataFormatter = dataFormatter;
	}

	public int getIdChamado() {
		return idChamado;
	}

	public void setIdChamado(int idChamado) {
		this.idChamado = idChamado;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdTecnico() {
		return idTecnico;
	}

	public void setIdTecnico(int idTecnico) {
		this.idTecnico = idTecnico;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public String getSolucao() {
		return solucao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}
	
	
}
