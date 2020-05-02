package com.giovanildo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tab_competidor_em_campo")
public class CompetidorEmCampo {

	private int id;
	private Competidor competidor;
	private Partida partida;
	private int gols;
	private boolean jogaEmCasa;

	@Id
	@GeneratedValue
	@Column(name = "competidor_em_campo_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name = "competidor_id")
	public Competidor getCompetidor() {
		return competidor;
	}

	public void setCompetidor(Competidor competidor) {
		this.competidor = competidor;
	}

	public int getGols() {
		return gols;
	}

	public void setGols(int gols) {
		this.gols = gols;
	}

	public CompetidorEmCampo() {
		super();
		this.gols = 0;
	}

	public CompetidorEmCampo(Partida partida, Competidor competidor, boolean jogaEmCasa) {
		super();
		this.competidor = competidor;
		this.partida = partida;
		this.gols = 0;
		this.jogaEmCasa = jogaEmCasa;
	}
	
	public CompetidorEmCampo(Partida partida, Competidor competidor) {
		super();
		this.competidor = competidor;
		this.partida = partida;
		this.gols = 0;
	}

//	@Override
//	public String toString() {
//		return "CompetidorEmCampo [competidor=" + competidor + ", gols=" + gols + "]";
//	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((competidor == null) ? 0 : competidor.hashCode());
		result = prime * result + gols;
		return result;
	}

	@Override
	public String toString() {
		return "CompetidorEmCampo [competidor=" + competidor + ", partida=" + partida + ", gols=" + gols
				+ ", jogaEmCasa=" + jogaEmCasa + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompetidorEmCampo other = (CompetidorEmCampo) obj;
		if (competidor == null) {
			if (other.competidor != null)
				return false;
		} else if (!competidor.equals(other.competidor))
			return false;
		if (gols != other.gols)
			return false;
		return true;
	}

	@ManyToOne
	@JoinColumn(name = "partida_id")
	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public boolean isJogaEmCasa() {
		return jogaEmCasa;
	}

	public void setJogaEmCasa(boolean jogaEmCasa) {
		this.jogaEmCasa = jogaEmCasa;
	}

}
