package com.giovanildo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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

	/**
	 * 
	 * @return o formulário de cadastro de eatletas
	 */
	public String formTorneio() {
		return "torneio?faces-redirect=true";
	}

	/**
	 * 
	 * @return o formulário de cadastro de eatletas
	 */
	public String formEatleta() {
		return "eatleta?faces-redirect=true";
	}

	/**
	 * 
	 * @return o formulário de cadastro de clubes
	 */
	public String formClube() {
		System.out.println("chamando a página de cadastro de clubes");
		return "clube.xhtml?faces-redirect=true";
	}

	/**
	 * persiste um novo torneio
	 */
	public void salvarTorneio() {
		System.out.println("Torneio :" + torneio.getNome() + " " + torneio.getPorqueDoNome());

		if (getCompetidores().size() < 2) {
			FacesContext.getCurrentInstance().addMessage("competidor",
					new FacesMessage("tem que ter pelo menos dois competidores"));
			return;
		}

		new DAO<Torneio>(Torneio.class).adiciona(torneio);

		torneio = new Torneio();
	}

	/**
	 * adiciona um novo competidor no torneio
	 * 
	 */
	public void incluirCompetidor() {
		Competidor competidor = new Competidor();
		competidor.setClube(new DAO<Clube>(Clube.class).buscaPorId(clubeId));
		competidor.seteAtleta(new DAO<EAtleta>(EAtleta.class).buscaPorId(eatletaId));
		competidor.setTorneio(this.torneio);

		this.getCompetidores().add(competidor);
	}

	/**
	 * 
	 * @return chama o gerarPartidas
	 */
	public List<Partida> getPartidas() {
		if (this.getCompetidores().size() < 2) {
			return null;
		}
		return geraPartidas(this.getCompetidores());
	}

	public void salvaPartidas() {
		System.out.println("salvando partidas");
		for (Partida partida : geraPartidas(getCompetidores())) {
			partida.setCompetidoresEmCampo(Arrays.asList(partida.getAnfitriao(), partida.getVisitante()));
			new DAO<Partida>(Partida.class).adiciona(partida);
		}
		FacesContext.getCurrentInstance().addMessage("partida", new FacesMessage("deu certo, gerar as partidas! ;)"));
	}

	/**
	 * gera lista de partidas com turno e returno
	 * 
	 * @param listaCompetidores
	 * @return
	 */
	public List<Partida> geraPartidas(List<Competidor> listaCompetidores) {
		List<Partida> partidas = new ArrayList<Partida>();
		List<Competidor> competidores = new ArrayList<Competidor>(listaCompetidores);

		boolean verificaSeQtdCompetidoresImpar = competidores.size() % 2 == 1;
		if (verificaSeQtdCompetidoresImpar) {
			competidores.add(0, null);
		}

		for (int rodada = 0; rodada < competidores.size() - 1; rodada++) {
			System.out.print((rodada + 1) + "a rodada: ");
			for (int jogo = 0; jogo < (competidores.size() / 2); jogo++) {
				boolean foraDaRodada = competidores.get(jogo) == null;
				if (foraDaRodada) {
					continue;
				}
				partidas.add(colocaPartida(competidores, jogo, mandoDeCampo(jogo, rodada)));
			}
			// Gira os clubes no sentido horário, mantendo o primeiro no lugar
			Competidor competidorRemovido = competidores.remove(competidores.size() - 1);
			competidores.add(1, competidorRemovido);
		}

		geraReturno(partidas);

		return partidas;
	}

	/**
	 * inverte o mando de campo das partidas, gerando assim o returno.
	 * 
	 * @param partidas
	 */
	private void geraReturno(List<Partida> partidas) {
		List<Partida> partidasTurno = new ArrayList<Partida>(partidas);
		for (Partida partidaTurno : partidasTurno) {
			partidaTurno.inverterMandoDeCampo();
			Partida partidaReturno = new Partida(partidaTurno.getCompetidoresEmCampo());
			partidas.add(partidaReturno);
		}
	}

	/**
	 * Teste para ajustar o mando de campo
	 * 
	 * @param jogo
	 * @param rodada rodadas
	 * @return
	 */
	private boolean mandoDeCampo(int jogo, int rodada) {
		return (jogo % 2 == 1 || rodada % 2 == 1 && jogo == 0);
	}

	/**
	 * 
	 * 
	 * @param competidores
	 * @param jogo
	 * @param mandoDeCampo
	 * @return Retorna uma partida baseada no mando de campo
	 */
	private Partida colocaPartida(List<Competidor> competidores, int jogo, boolean mandoDeCampo) {
		Partida partida = null;
		Competidor c1 = competidores.get(competidores.size() - jogo - 1);
		Competidor c2 = competidores.get(jogo);
		CompetidorEmCampo anfitriao = new CompetidorEmCampo(partida, c1, mandoDeCampo);
		CompetidorEmCampo visitante = new CompetidorEmCampo(partida, c2, !mandoDeCampo);
		partida = new Partida(Arrays.asList(anfitriao, visitante));
		return partida;
	}

	/**
	 * valida o nome do torneio, impedindo criação de torneio sem e torneio com nome
	 * repetido
	 * 
	 * @param fc
	 * @param component
	 * @param value
	 * @throws ValidatorException
	 */
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

	/**
	 * 
	 * @return lista dos competidores do torneio
	 */
	public List<Competidor> getCompetidores() {
		return this.torneio.getCompetidores();
	}

	/**
	 * 
	 * @return retorna uma lista dos torneios já registrados
	 */
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
