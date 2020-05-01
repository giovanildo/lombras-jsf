package com.giovanildo.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tab_torneio")
public class Torneio {
	/**
	 * id do torneio
	 */
	private int id;
	/**
	 * nome do torneio
	 */
	private String nome;
	/**
	 * Explicação do nome
	 */
	private String porqueDoNome;

	/**
	 * Data em que o torneio foi jogado
	 */

	private Calendar dataTorneio;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_torneio", nullable = false)
	public Calendar getDataTorneio() {
		return dataTorneio;
	}

	public void setDataTorneio(Calendar dataTorneio) {
		this.dataTorneio = dataTorneio;
	}

	/**
	 * Pessoas que vão jogar no torneio
	 */
	private List<Competidor> competidores = new ArrayList<Competidor>();

	/**
	 * 
	 * @return id
	 */
	@Id
	@GeneratedValue
	@Column(name = "torneio_id")
	public Integer getId() {
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
	 * @param nome
	 * @param porqueDoNome
	 */
	public Torneio(String nome, String porqueDoNome, List<Competidor> competidores) {
		super();
		this.dataTorneio = Calendar.getInstance();
		this.nome = nome;
		this.porqueDoNome = porqueDoNome;
		this.competidores = competidores;
	}

	public Torneio() {
		super();
		this.dataTorneio = Calendar.getInstance();
	}

	@Override
	public String toString() {

		return nome + "  " + porqueDoNome;
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

	@OneToMany(mappedBy = "torneio", cascade = CascadeType.ALL)
	public List<Competidor> getCompetidores() {
		return competidores;
	}

	public void setCompetidores(List<Competidor> competidores) {
		this.competidores = competidores;
	}

}