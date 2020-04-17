package com.giovanildo.models;

/**
 * 
 * @author giovanildo Classe criada para armazenar a classificação
 *
 */
public class Classificacao implements Comparable<Classificacao> {
	/**
	 * Nome do Clube
	 */
	private String clube;
	/**
	 * Pontuação do EAtleta no Torneio
	 */
	private Integer pontos;
	/**
	 * Qtd de partidas jogadas
	 */
	private int jogos;
	/**
	 * Qtd de vitórias
	 */
	private int vitorias;
	/**
	 * qtd de empates
	 */
	private int empates;
	/**
	 * qtd de derrotas
	 */
	private int derrotas;
	/**
	 * qtd de gols a favor
	 */
	private int golspro;
	/**
	 * qtd de gols contra
	 */
	private int golscontra;
	/**
	 * saldo de gols
	 */
	private int saldo;
	/**
	 * aproveitamento
	 */
	private int aproveitamento;

	/**
	 * 
	 * @return clube
	 */
	public String getClube() {
		return clube;
	}

	/**
	 * 
	 * @param clube
	 */
	public void setClube(String clube) {
		this.clube = clube;
	}

	/**
	 * 
	 * @return pontos
	 */
	public int getPontos() {
		return pontos;
	}

	/**
	 * 
	 * @param pontos
	 */
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	/**
	 * 
	 * @return qtd jogos
	 */
	public int getJogos() {
		return jogos;
	}

	/**
	 * 
	 * @param jogos
	 */
	public void setJogos(int jogos) {
		this.jogos = jogos;
	}

	/**
	 * 
	 * @return qtd vitorias
	 */
	public int getVitorias() {
		return vitorias;
	}

	/**
	 * 
	 * @param qtd
	 *            vitorias
	 */
	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}

	/**
	 * 
	 * @return qtd empates
	 */
	public int getEmpates() {
		return empates;
	}

	/**
	 * 
	 * @param empates
	 */
	public void setEmpates(int empates) {
		this.empates = empates;
	}

	/**
	 * 
	 * @return qtd derrotas
	 */
	public int getDerrotas() {
		return derrotas;
	}

	/**
	 * 
	 * @param derrotas
	 */
	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}

	/**
	 * 
	 * @return qtd golspro
	 */
	public int getGolspro() {
		return golspro;
	}

	/**
	 * 
	 * @param golspro
	 */
	public void setGolspro(int golspro) {
		this.golspro = golspro;
	}

	/**
	 * 
	 * @return golscontra
	 */
	public int getGolscontra() {
		return golscontra;
	}

	/**
	 * 
	 * @param golscontra
	 */
	public void setGolscontra(int golscontra) {
		this.golscontra = golscontra;
	}

	/**
	 * 
	 * @return saldo de gols
	 */
	public int getSaldo() {
		return saldo;
	}

	/**
	 * 
	 * @param saldo
	 *            de gols
	 */
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	/**
	 * 
	 * @return aproveitamento
	 */
	public int getAproveitamento() {
		return aproveitamento;
	}

	public void setAproveitamento(int aproveitamento) {
		this.aproveitamento = aproveitamento;
	}

	@Override
	public String toString() {
		return "Clube" + clube + ", Pt" + pontos + ", jg=" + jogos + ", vit=" + vitorias + ", emp=" + empates + ", der="
				+ derrotas + ", gp=" + golspro + ", gc=" + golscontra + ", sg=" + saldo + ", aprov=" + aproveitamento;
	}

	/**
	 * 
	 * @param clube
	 * @param pontos
	 * @param jogos
	 * @param vitorias
	 * @param empates
	 * @param derrotas
	 * @param golspro
	 * @param golscontra
	 * @param saldo
	 * @param aproveitamento
	 */
	public Classificacao(String clube, int pontos, int jogos, int vitorias, int empates, int derrotas, int golspro,
			int golscontra, int saldo, int aproveitamento) {
		super();
		this.clube = clube;
		this.pontos = pontos;
		this.jogos = jogos;
		this.vitorias = vitorias;
		this.empates = empates;
		this.derrotas = derrotas;
		this.golspro = golspro;
		this.golscontra = golscontra;
		this.saldo = saldo;
		this.aproveitamento = aproveitamento;
	}

	public int compareTo(Classificacao outro) {
		Integer p = this.pontos;
		Integer o = outro.getPontos();
		return o.compareTo(p);
	}

}