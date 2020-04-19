package com.giovanildo.models;

/**
 * 
 * @author giovanildo classe que configura as partidas
 */
public class Partida {

	/**
	 * id único
	 */
	private int id;
	/**
	 * clube que joga em casa
	 */
	private CompetidorEmCampo anfitriao;
	/**
	 * clube que joga fora
	 */
	private CompetidorEmCampo visitante;
	/**
	 * saber se a partida foi encerrada ou não
	 */

	private boolean encerrada;

	
	/**
	 * 
	 * @param id
	 * @param anfitriao
	 * @param visitante
	 * @param encerrada
	 */
	public Partida(CompetidorEmCampo anfitriao, CompetidorEmCampo visitante) {
		super();
		this.anfitriao = anfitriao;
		this.visitante = visitante;
		this.encerrada = false;
	}
	
	/**
	 * Exigência do JPA 
	 */
	public Partida() {}
	

	/**
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return o anfitrião da partida
	 */

	public CompetidorEmCampo getAnfitriao() {
		return anfitriao;
	}

	/**
	 * 
	 * @return a equipe que joga fora de casa
	 */
	public CompetidorEmCampo getVisitante() {
		return visitante;
	}

	/**
	 * 
	 * @param anfitriao
	 */
	public void setAnfitriao(CompetidorEmCampo anfitriao) {
		this.anfitriao = anfitriao;
	}

	/**
	 * 
	 * @param visitante
	 */

	public void setVisitante(CompetidorEmCampo visitante) {
		this.visitante = visitante;
	}
	

	/**
	 * @return the encerrada verifica se a partida está encerrada
	 */
	public boolean isEncerrada() {

		return encerrada;
	}

	/**
	 * @param encerrada
	 *            the encerrada to set
	 */
	public void setEncerrada(boolean encerrada) {
		this.encerrada = encerrada;
	}

	@Override
	public String toString() {
		String clubeMandante = anfitriao.getCompetidor().getClube().getNome();
		String clubeVisitante = visitante.getCompetidor().getClube().getNome();
		String eAtletaMandante = anfitriao.getCompetidor().geteAtleta().getNome().toUpperCase();
		String eAtletaVisitante = visitante.getCompetidor().geteAtleta().getNome().toUpperCase();

		int golsMandante = this.anfitriao.getGols();
		int golsVisitante = this.visitante.getGols();

		return eAtletaMandante + " - " + clubeMandante + " " + golsMandante + " x " + golsVisitante + " "
				+ clubeVisitante + " - " + eAtletaVisitante;
	}
}