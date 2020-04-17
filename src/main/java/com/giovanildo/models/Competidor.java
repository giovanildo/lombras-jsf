package com.giovanildo.models;

/**
 * 
 * @author giovanildo classe que guarda as informações do clube/jogador de vídeo
 *         game na partida
 */
//@Entity
//@Table(name = "competidor")
public class Competidor {
	/**
	 * id único do eatleta no torneio
	 */
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(name = "competidor_id")
	private int id;

	/**
	 * Jogador de Video Game
	 */
	
//	@Column
	private EAtleta eAtleta;

	/**
	 * clube que o jogador de vídeogame vai jogar
	 */
//	@OneToOne
//	@Column
	private Clube clube;

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
	 * 
	 * @return o jogador de videogame
	 */
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
	 * @param torneio
	 * @param clube
	 * 
	 */

	public Competidor(EAtleta eAtleta, Torneio torneio, Clube clube) {
		super();
		this.eAtleta = eAtleta;
		this.clube = clube;
	}
	public Competidor() {
		super();
	}

	@Override
	public String toString() {
		return eAtleta + "   " + clube + "   ";
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

}
