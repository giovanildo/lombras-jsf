package com.giovanildo.models;


import java.util.List;

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
@Table(name = "tab_partida")
public class Partida {

	/**
	 * id único
	 */
	private int id;

	private List<CompetidorEmCampo> competidoresEmCampo;

	/**
	 * saber se a partida foi encerrada ou não
	 */

	private boolean encerrada;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((competidoresEmCampo == null) ? 0 : competidoresEmCampo.hashCode());
		result = prime * result + (encerrada ? 1231 : 1237);
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partida other = (Partida) obj;
		if (competidoresEmCampo == null) {
			if (other.competidoresEmCampo != null)
				return false;
		} else if (!competidoresEmCampo.equals(other.competidoresEmCampo))
			return false;
		if (encerrada != other.encerrada)
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * Exigência do JPA
	 */
	public Partida() {
		super();
		this.encerrada = false;
	}

//	public Partida(CompetidorEmCampo anfitriao, CompetidorEmCampo visitante) {
//		super();
//		anfitriao.setJogaEmCasa(true);
//		visitante.setJogaEmCasa(false);
//
//		this.competidoresEmCampo = new ArrayList<CompetidorEmCampo>();
//		
//		this.competidoresEmCampo.add(0, anfitriao);
//		this.competidoresEmCampo.add(1, visitante);		
//		
//		this.encerrada = false;
//	}
	

	public Partida(List<CompetidorEmCampo> competidoresEmCampo) {
		super();
		this.competidoresEmCampo = competidoresEmCampo;
		this.encerrada = false;
	}

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
	 * @param encerrada the encerrada to set
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
	public List<CompetidorEmCampo> getCompetidoresEmCampo() {
		return competidoresEmCampo;
	}

	@Override
	public String toString() {
		return "Partida [id=" + id + ", competidoresEmCampo=" + competidoresEmCampo + ", encerrada=" + encerrada + "]";
	}

	public void setCompetidoresEmCampo(List<CompetidorEmCampo> competidoresEmCampo) {
		this.competidoresEmCampo = competidoresEmCampo;
	}
}