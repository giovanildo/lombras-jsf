package com.giovanildo.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author giovanildo classe que configura as partidas
 */
@Entity
@Table (name = "tab_partida")
public class Partida {

	/**
	 * id único
	 */
	private int id;
	
	
	private Collection<CompetidorEmCampo> competidoresEmCampo;
	
	/**
	 * saber se a partida foi encerrada ou não
	 */

	private boolean encerrada;
	
	/**
	 * Exigência do JPA 
	 */
	public Partida() {}
	

	/**
	 * 
	 * @return id
	 */
	@Id
	@GeneratedValue
	@Column(name = "partida_id")
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

//	@Override
//	public String toString() {
//		String clubeMandante = anfitriao.getCompetidor().getClube().getNome();
//		String clubeVisitante = visitante.getCompetidor().getClube().getNome();
//		String eAtletaMandante = anfitriao.getCompetidor().geteAtleta().getNome().toUpperCase();
//		String eAtletaVisitante = visitante.getCompetidor().geteAtleta().getNome().toUpperCase();
//
//		int golsMandante = this.anfitriao.getGols();
//		int golsVisitante = this.visitante.getGols();
//
//		return eAtletaMandante + " - " + clubeMandante + " " + golsMandante + " x " + golsVisitante + " "
//				+ clubeVisitante + " - " + eAtletaVisitante;
//	}
	@OneToMany(mappedBy = "partida")
	public Collection<CompetidorEmCampo> getCompetidoresEmCampo() {
		return competidoresEmCampo;
	}

	public void setCompetidoresEmCampo(Collection<CompetidorEmCampo> competidoresEmCampo) {
		this.competidoresEmCampo = competidoresEmCampo;
	}
}