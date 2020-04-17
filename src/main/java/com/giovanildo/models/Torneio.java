package com.giovanildo.models;

import java.util.Collection;

//@Entity
//@Table(name = "torneio")
public class Torneio {
	/**
	 * id do torneio
	 */
//	@Id
	private int id;
	/**
	 * nome do torneio
	 */
//	@Column
	private String nome;
	/**
	 * Explicação do nome
	 */
//	@Column
	private String porqueDoNome;

//	@ManyToMany
//	@JoinTable (name="torneio_tem_competidores", 
//	joinColumns = {@JoinColumn(name = "torneio_id")},
//	inverseJoinColumns = {@JoinColumn (name="competidor_id")})
	private Collection<Competidor> competidores;
	
	/**
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @return Porque do Nome
	 */
	public String getPorqueDoNome() {
		return porqueDoNome;
	}

	public void setPorqueDoNome(String porqueDoNome) {
		this.porqueDoNome = porqueDoNome;
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
	 * @param nome torneio
	 */
	public Torneio(String nome, String porqueDoNome) {
		super();
		this.nome = nome;
		this.porqueDoNome = porqueDoNome;
	}
	
	public Torneio() {
		super();		
	}

	@Override
	public String toString() {
		return nome;
	}

	/**
	 * 
	 * @return nome do torneio
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * @param nome
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
		Torneio other = (Torneio) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public Collection<Competidor> getCompetidores() {
		return competidores;
	}

	public void setCompetidores(Collection<Competidor> competidores) {
		this.competidores = competidores;
	}
}