package com.giovanildo.models;

import javax.persistence.*;

/**
 * 
 * @author giovanildo
 *
 *         Essa classe grava informações referente ao Clube de Futebol
 */
@Entity
@Table(name = "tab_clube")
public class Clube {
	/**
	 * id único do clube
	 */

	private int id;
	/**
	 * nome do clube
	 */
	private String nome;

	/**
	 * Nacionalidade do Clube
	 */
	private String nacionalidade;

	/**
	 * construtor jpa
	 */
	public Clube() {	}

	public Clube(String nome, String nacionalidade) {
		super();
		this.nome = nome;
		this.nacionalidade = nacionalidade;
	}
	
	@Column(length=60,  nullable = false)
	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	@Id
	@GeneratedValue
	@Column(name = "clube_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return nome;
	}

	/**
	 * 
	 * @return String nome
	 */
	@Column(length=60,  nullable = false)
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * @param nome permite colocar o nome do clube
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
		Clube other = (Clube) obj;
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
