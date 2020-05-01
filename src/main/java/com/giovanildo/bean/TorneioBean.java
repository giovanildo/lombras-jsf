package com.giovanildo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.giovanildo.dao.DAO;
import com.giovanildo.models.Clube;
import com.giovanildo.models.Competidor;
import com.giovanildo.models.CompetidorEmCampo;
import com.giovanildo.models.EAtleta;
import com.giovanildo.models.Partida;
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

		if (getCompetidores().size() < 2) {
			// throw new RuntimeException("Um torneio de pelo menos dois competidores");
			FacesContext.getCurrentInstance().addMessage("competidor",
					new FacesMessage("tem que ter pelo menos dois competidores"));
			return;
		}

		new DAO<Torneio>(Torneio.class).adiciona(torneio);

		torneio = new Torneio();
	}

	public void incluirCompetidor() {

		Clube clube = new DAO<Clube>(Clube.class).buscaPorId(clubeId);
		EAtleta eatleta = new DAO<EAtleta>(EAtleta.class).buscaPorId(eatletaId);
		Competidor competidor = new Competidor();

		competidor.setClube(clube);
		competidor.seteAtleta(eatleta);
		competidor.setTorneio(this.torneio);

		this.getCompetidores().add(competidor);
	}

	public List<Partida> getPartidas() {
		return geraPartidas(this.getCompetidores());
	}

	/**
	 * gera array de partidas
	 */
	public List<Partida> geraPartidas(List<Competidor> competidores) {

		List<Partida> listaPartidas = new ArrayList<Partida>();

		// em caso de partidas clubes impares
		if (competidores.size() % 2 == 1) {
			competidores.add(0, null);
		}

		// variaveis que serao base para gerar partidas
		int totalClubes = competidores.size();
		int metadeClubes = totalClubes / 2;
		Partida partida = null;
		List<CompetidorEmCampo> competidoresEmCampo = null;
		for (int turno = 0; turno <= 1; turno++) {
			for (int t = 0; t < (totalClubes - 1); t++) {// for das rodadas
				for (int m = 0; m < metadeClubes; m++) {// for dos jogos
					// Clube está de fora nessa rodada?
					if (competidores.get(m) == null) {
						continue;
					}
					// Teste para ajustar o mando de campo
					if (m % 2 == 1 || t % 2 == 1 && m == 0) {
						if (turno == 0) {
							partida = new Partida();
							competidoresEmCampo = new ArrayList<CompetidorEmCampo>();
							// guardando o anfitrião
							competidoresEmCampo.add(
									new CompetidorEmCampo(partida, competidores.get(totalClubes - m - 1), 0, true));
							// guardando o visitante
							competidoresEmCampo.add(new CompetidorEmCampo(partida, competidores.get(m), 0, false));
						} else {
							partida = new Partida();
							competidoresEmCampo = new ArrayList<CompetidorEmCampo>();
							// guardando o anfitrião
							competidoresEmCampo.add(new CompetidorEmCampo(partida, competidores.get(m), 0, true));
							// guardando o visitante
							competidoresEmCampo.add(
									new CompetidorEmCampo(partida, competidores.get(totalClubes - m - 1), 0, false));
						}
					} else {

						if (turno == 1) {
							partida = new Partida();
							competidoresEmCampo = new ArrayList<CompetidorEmCampo>();
							// guardando o anfitrião
							competidoresEmCampo.add(new CompetidorEmCampo(partida, competidores.get(m), 0, true));
							// guardando o visitante
							competidoresEmCampo.add(
									new CompetidorEmCampo(partida, competidores.get(totalClubes - m - 1), 0, false));
						} else {
							partida = new Partida();
							competidoresEmCampo = new ArrayList<CompetidorEmCampo>();
							// guardando o anfitrião
							competidoresEmCampo.add(
									new CompetidorEmCampo(partida, competidores.get(totalClubes - m - 1), 0, true));
							// guardando o visitante
							competidoresEmCampo.add(new CompetidorEmCampo(partida, competidores.get(m), 0, false));
						}
					}
				}

				partida.setCompetidoresEmCampo(competidoresEmCampo);
				// inserindo na lista

				listaPartidas.add(partida);

				// Gira os clubes no sentido horário, mantendo o primeiro no lugar
				Competidor remove = competidores.remove(competidores.size() - 1);
				competidores.add(1, remove);
			}
		}

		return listaPartidas;
	}

	public void validadorNome(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String nome = value.toString();

		if (nome.isEmpty()) {
			throw new ValidatorException(
					new FacesMessage("Nome do Torneio Vazio, escreve alguma coisa, seu arrombado"));
		}

		for (Torneio daVez : new DAO<Torneio>(Torneio.class).listaTodos()) {
			if (daVez.getNome().equals(nome)) {
				throw new ValidatorException(new FacesMessage("Torneio com o nome" + nome + " já existe"));
			}
		}
	}

	public List<Competidor> getCompetidores() {
		return this.torneio.getCompetidores();
	}

	public List<Torneio> getTorneios() {
		return new DAO<Torneio>(Torneio.class).listaTodos();
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
