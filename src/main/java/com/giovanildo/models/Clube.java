package com.giovanildo.models;

import javax.persistence.*;

/**
 * 
 * @author giovanildo
 *
 *         Essa classe grava informações referente ao Clube de Futebol
 */
@Entity
//@Table(name = "clube")
public class Clube {
	/**
	 * id único do clube
	 */
	
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "clube_id")
	private int id;
	/**
	 * nome do clube
	 */
//	@Column
	private String nome;
	
		
	public Clube() {
		super();
	}

	/**
	 * Nacionalidade do Clube
	 */
//	@Column
	private String nacionalidade;
	
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	@Id
	@GeneratedValue
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
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * @param nome
	 *            permite colocar o nome do clube
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
