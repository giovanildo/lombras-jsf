package com.giovanildo.jpa.service.ui;

import java.util.ArrayList;
import java.util.List;

import com.giovanildo.jpa.service.interfaces.CrudService;
import com.giovanildo.models.Clube;
import com.giovanildo.models.Competidor;
import com.giovanildo.models.CompetidorEmCampo;
import com.giovanildo.models.EAtleta;
import com.giovanildo.models.Partida;
import com.giovanildo.models.Torneio;
import com.giovanildo.services.impl.ClubeService;
import com.giovanildo.services.impl.EatletaService;
import com.giovanildo.services.impl.PartidaService;
import com.giovanildo.services.impl.TorneioService;

public class Main {
	public static void main(String[] args) {
		System.out.println("Testando JPA - competidor");

		// instancia os objetos a serem persistidos e altera-os
		// instância um Crud Service
		// faz um foreach mostrando o conteudo da tabela

		CrudService<EAtleta, Integer> eaService = new EatletaService();
		CrudService<Clube, Integer> clubeService = new ClubeService();
		CrudService<Torneio, Integer> torneioService = new TorneioService();
		CrudService<Partida, Integer> partidaService = new PartidaService();
		

		// instancia os objetos a serem persistidos e altera-os
		// instância um Crud Service
		// faz um foreach mostrando o conteudo da tabela

		// adicionando clubes

//		clubeService.insert(new Clube("Borrusia", "Alemanha"));
//		clubeService.insert(new Clube("Real", "Espanha"));
//		clubeService.insert(new Clube("Manchester", "Inglaterra"));
//		clubeService.insert(new Clube("Porto", "Portugal"));

		// adicionando Eatletas

//		eaService.insert(new EAtleta("Giovanildo"));
//		eaService.insert(new EAtleta("Genilson"));
//		eaService.insert(new EAtleta("Fabiano"));
//		eaService.insert(new EAtleta("Zaldir"));

		// adicionando Torneio/Competidores

		Torneio torneio = new Torneio();
		torneio.setNome("Cachorro na rua");
		torneio.setPorqueDoNome("correndo ");

		List<Competidor> competidores = new ArrayList<>();

		competidores.add(new Competidor(torneio, eaService.byString("Giovanildo"), clubeService.byString("Porto")));
		competidores.add(new Competidor(torneio, eaService.byString("Genilson"), clubeService.byString("Manchester")));
		competidores.add(new Competidor(torneio, eaService.byString("Fabiano"), clubeService.byString("Real")));
		competidores.add(new Competidor(torneio, eaService.byString("Zaldir"), clubeService.byString("Borrusia")));

		torneio.setCompetidores(competidores);

		torneioService.insert(torneio);

		System.out.println(" --------------- Listando Dados ---------------------");
		
		Partida partida = new Partida();
		partida.setEncerrada(false);
		ArrayList<CompetidorEmCampo> competidoresEmCampo = new ArrayList<CompetidorEmCampo>();
		//guardando o anfitrião
		competidoresEmCampo.add(new CompetidorEmCampo(competidores.get(0), 0, true));
		//guardando o visitante
		competidoresEmCampo.add(new CompetidorEmCampo(competidores.get(1), 0, false));
		
		partida.setCompetidoresEmCampo(competidoresEmCampo);
		
		partidaService.insert(partida);
		
		//geraPartidas(competidores);
		
		System.out.println(" --------------- Listando Dados ---------------------");
		
		
		for (Clube daVez : clubeService.all()) {
			System.out.println(daVez.toString());
		}

		for (EAtleta daVez : eaService.all()) {
			System.out.println(daVez.toString());
		}

		for (Torneio daVez : torneioService.all()) {
			System.out.println(daVez.toString());
		}

		System.out.println("Inserido com sucesso!");

	}

	/**
	 * gera array de partidas
	 */
	public static List<Partida> geraPartidas(List<Competidor> competidores) {
		List<Partida> listaPartidas = new ArrayList<Partida>();
		
		CrudService<Partida, Integer> partidaService = new PartidaService();
		
		// em caso de partidas clubes impares
		if (competidores.size() % 2 == 1) {
			competidores.add(0, null);
		}

		// variaveis que serao base para gerar partidas
		int totalClubes = competidores.size();
		int metadeClubes = totalClubes / 2;
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
							competidoresEmCampo = new ArrayList<CompetidorEmCampo>();
							//guardando o anfitrião
							competidoresEmCampo.add(new CompetidorEmCampo(competidores.get(totalClubes - m - 1), 0, true));
							//guardando o visitante
							competidoresEmCampo.add(new CompetidorEmCampo(competidores.get(m), 0, false));
						} else {
							
							competidoresEmCampo = new ArrayList<CompetidorEmCampo>();
							//guardando o anfitrião
							competidoresEmCampo.add(new CompetidorEmCampo(competidores.get(m), 0, true));
							//guardando o visitante
							competidoresEmCampo.add(new CompetidorEmCampo(competidores.get(totalClubes - m - 1), 0, false));
						}
					} else {					
												
						if (turno == 1) {
							competidoresEmCampo = new ArrayList<CompetidorEmCampo>();
							//guardando o anfitrião
							competidoresEmCampo.add(new CompetidorEmCampo(competidores.get(m), 0, true));
							//guardando o visitante
							competidoresEmCampo.add(new CompetidorEmCampo(competidores.get(totalClubes - m - 1), 0, false));
						} else {
							
							competidoresEmCampo = new ArrayList<CompetidorEmCampo>();
							//guardando o anfitrião
							competidoresEmCampo.add(new CompetidorEmCampo(competidores.get(totalClubes - m - 1), 0, true));
							//guardando o visitante
							competidoresEmCampo.add(new CompetidorEmCampo(competidores.get(m), 0, false));
						}
					}
				}
				
				Partida partida = new Partida();
				partida.setCompetidoresEmCampo(competidoresEmCampo);
				// inserindo na lista
				partidaService.insert(partida);
				listaPartidas.add(partida);
				
				

				// Gira os clubes no sentido horário, mantendo o primeiro no lugar
				Competidor remove = competidores.remove(competidores.size() - 1);
				competidores.add(1, remove);
			}
		}
		
		return listaPartidas;

	}

}
