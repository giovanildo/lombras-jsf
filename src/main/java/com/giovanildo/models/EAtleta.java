package com.giovanildo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author giovanildo classe grava informações do jogador de videogame
 */
@Entity
@Table(name = "tab_eatleta")
public class EAtleta {

	/**
	 * id único do EAtleta
	 */

	private int id;
	/**
	 * Nome do Jogador de Vídeo Game
	 */
//	@Column
	private String nome;

	/**
	 * 
	 * @return id
	 */
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return nome;
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
	 * @return nome do tipo String
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * @param nome
	 *            do eatleta
	 */
	public EAtleta(String nome) {
		super();
		this.nome = nome;
	}

	public EAtleta() {
		super();
	}
	/**
	 * 
	 * @param nome
	 *            do eatleta
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EAtleta other = (EAtleta) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
