package com.giovanildo.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.giovanildo.dao.DAO;
import com.giovanildo.models.Clube;
import com.giovanildo.models.Competidor;
import com.giovanildo.models.EAtleta;
import com.giovanildo.models.Torneio;

@Named
@ViewScoped
public class TorneioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Torneio torneio = new Torneio();

	private Integer clubeId;

	private Integer eatletaId;

	public void salvarTorneio() {
		System.out.println("Torneio :" + torneio.getNome() + " " + torneio.getPorqueDoNome());
		
		if(getClubes().isEmpty()) {
			throw new RuntimeException("A lista de clubes deve haver pelo menos um");
		}
		
		new DAO<Torneio>(Torneio.class).adiciona(torneio); 
	}

	public void incluirCompetidor() {
		
	}

	public List<Competidor> getCompetidores() {
		return this.torneio.getCompetidores();
	}

	/**
	 * 
	 * @return a lista de clubes para mostrar no selectOneMenu de clubes
	 */
	public List<Clube> getClubes() {
		return new DAO<Clube>(Clube.class).listaTodos();
	}

	/**
	 * 
	 * @return a lista de eatletas para mostrar no selectOneMenu de eatletas
	 */
	public List<EAtleta> getEatletas() {
		return new DAO<EAtleta>(EAtleta.class).listaTodos();
	}

	public Torneio getTorneio() {
		return torneio;
	}

	public void setTorneio(Torneio torneio) {
		this.torneio = torneio;
	}

	public Integer getClubeId() {
		return clubeId;
	}

	public void setClubeId(Integer clubeId) {
		this.clubeId = clubeId;
	}

	public Integer getEatletaId() {
		return eatletaId;
	}

	public void setEatletaId(Integer eatletaId) {
		this.eatletaId = eatletaId;
	}

}
