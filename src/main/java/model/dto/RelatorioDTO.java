package model.dto;

public class RelatorioDTO {
	
	private String nome;
	private int qtdChamados;
	private double mediaResolução;
	
	public RelatorioDTO(String nome, int qtdChamados, double mediaResolução) {
		super();
		this.nome = nome;
		this.qtdChamados = qtdChamados;
		this.mediaResolução = mediaResolução;
	}

	public RelatorioDTO() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtdChamados() {
		return qtdChamados;
	}

	public void setQtdChamados(int qtdChamados) {
		this.qtdChamados = qtdChamados;
	}

	public double getMediaResolução() {
		return mediaResolução;
	}

	public void setMediaResolução(double mediaResolução) {
		this.mediaResolução = mediaResolução;
	}

	public void imprimir() {
		System.out.printf("\n%10s  %10s  %10s  ",
				this.getNome(),
				this.getQtdChamados(),
				this.getMediaResolução());
	}
	
}
