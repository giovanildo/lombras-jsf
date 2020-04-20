package com.giovanildo.models;

import javax.persistence.*;

/**
 * 
 * @author giovanildo classe que guarda as informações do clube/jogador de vídeo
 *         game na partida
 */
@Entity
@Table(name = "tab_competidor")
public class Competidor {
	/**
	 * id único do eatleta no torneio
	 */

	private int id;

	/**
	 * Jogador de Video Game
	 */
	private EAtleta eAtleta;

	/**
	 * clube que o jogador de vídeogame vai jogar
	 */
	private Clube clube;
	/**
	 * Torneio em que o competidor vai jogar
	 */
	private Torneio torneio;

	/**
	 * 
	 * @return id
	 */
	@Id
	@GeneratedValue
	@Column(name = "competidor_id")
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
	 * 
	 * @return o jogador de videogame
	 */
	@OneToOne
	@JoinColumn(name = "eatleta_id", unique = false, nullable = false)
	public EAtleta geteAtleta() {
		return eAtleta;
	}

	/**
	 * 
	 * @param eAtleta o jogador de videogame
	 * 
	 */
	public void seteAtleta(EAtleta eAtleta) {
		this.eAtleta = eAtleta;
	}

	/**
	 * 
	 * @return o clube que o jogador de video game está jogando
	 */
	@OneToOne 
	@JoinColumn(name = "clube_id", unique = false, nullable = false)
	public Clube getClube() {
		return clube;
	}

	/**
	 * 
	 * @param clube
	 */
	public void setClube(Clube clube) {
		this.clube = clube;
	}

	/**
	 * 
	 * @param eAtleta
	 * @param clube
	 * 
	 */
	public Competidor(Torneio torneio, EAtleta eAtleta, Clube clube) {
		super();
		this.eAtleta = eAtleta;
		this.clube = clube;
		this.torneio = torneio;
	}

	public Competidor() {
		super();
	}

	@Override
	public String toString() {
		return eAtleta + "   " + clube + "  " + torneio;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Competidor other = (Competidor) obj;
		if (clube == null) {
			if (other.clube != null)
				return false;
		} else if (!clube.equals(other.clube))
			return false;
		if (eAtleta == null) {
			if (other.eAtleta != null)
				return false;
		} else if (!eAtleta.equals(other.eAtleta))
			return false;
		if (id != other.id)
			return false;

		return true;
	}
	@ManyToOne
	@JoinColumn(name = "torneio_id")
	public Torneio getTorneio() {
		return torneio;
	}

	public void setTorneio(Torneio torneio) {
		this.torneio = torneio;
	}

}
