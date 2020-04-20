package com.giovanildo.utils;

import java.util.ArrayList;
import java.util.List;

import com.giovanildo.models.Competidor;
import com.giovanildo.models.CompetidorEmCampo;
import com.giovanildo.models.Partida;

public class Controller {
	/**
	 * gera array de partidas
	 */
	public static List<Partida> geraPartidas(List<Competidor> competidores) {

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

}
