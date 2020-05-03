package com.giovanildo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	 * campo transiente, apenas para facilitar a busca nos dados
	 */
	private CompetidorEmCampo anfitriao;

	/**
	 * campo transiente, apenas para facilitar a busca nos dados
	 */
	private CompetidorEmCampo visitante;

	/**
	 * saber se a partida foi encerrada ou não
	 */

	private boolean encerrada;

	/**
	 * Exigência do JPA
	 */
	public Partida() {
		super();
		this.encerrada = false;
	}

	public Partida(List<CompetidorEmCampo> competidoresEmCampo) {
		super();
		this.competidoresEmCampo = competidoresEmCampo;

		geraAnfitriaoVisitante(competidoresEmCampo);
		this.encerrada = false;
	}
	/**
	 * define atributo visitante e anfitriao, baseado na lista de competidores
	 * atributo visitante e anfitriao não são mapeados no jpa
	 * @param competidoresEmCampo
	 */
	private void geraAnfitriaoVisitante(List<CompetidorEmCampo> competidoresEmCampo) {
		for (CompetidorEmCampo daVez : competidoresEmCampo) {
			if (daVez.isJogaEmCasa()) {
				anfitriao = daVez;
			} else {
				visitante = daVez;
			}
		}
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

	@Transient
	public CompetidorEmCampo getAnfitriao() {
		return anfitriao;
	}

	public void setAnfitriao(CompetidorEmCampo anfitriao) {
		this.anfitriao = anfitriao;
	}

	@Transient
	public CompetidorEmCampo getVisitante() {
		return visitante;
	}

	public void inverterMandoDeCampo() {
		for (CompetidorEmCampo daVez : competidoresEmCampo) {
			daVez.setJogaEmCasa(!daVez.isJogaEmCasa());			
		}
	}

	public void setVisitante(CompetidorEmCampo visitante) {
		this.visitante = visitante;
	}

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