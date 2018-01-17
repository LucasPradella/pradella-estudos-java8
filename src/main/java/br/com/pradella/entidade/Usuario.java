package br.com.pradella.entidade;

public class Usuario {
	private String nome;
	private int ponto;
	private boolean moderador;
	
	
	
	public Usuario(String nome, int ponto) {
		this.nome = nome;
		this.ponto = ponto;
		this.moderador = false;
	}
	
	
	public Usuario(String nome) {
		this.nome = nome;
	}
	
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPonto() {
		return ponto;
	}

	public void setPonto(int ponto) {
		this.ponto = ponto;
	}

	public boolean isModerador() {
		return moderador;
	}

	
	public void tornarModerador() {
		this.moderador = true;
	}

	
	

	
	public String toString() {
		return "Usuario " + nome;
	}

	

}
