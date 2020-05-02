package com.giovanildo.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.giovanildo.models.Classificacao;
import com.giovanildo.models.Competidor;
import com.giovanildo.models.CompetidorEmCampo;
import com.giovanildo.models.Partida;

public class Controller {
//	/**
//	 * gera array de partidas
//	 */
//	public static List<Partida> geraPartidas(List<Competidor> competidores) {
//
//		List<Partida> listaPartidas = new ArrayList<Partida>();
//
//		// em caso de partidas clubes impares
//		if (competidores.size() % 2 == 1) {
//			competidores.add(0, null);
//		}
//
//		// variaveis que serao base para gerar partidas
//		int totalClubes = competidores.size();
//		int metadeClubes = totalClubes / 2;
//		Partida partida = null;
//		List<CompetidorEmCampo> competidoresEmCampo = null;
//		for (int turno = 0; turno <= 1; turno++) {
//			for (int t = 0; t < (totalClubes - 1); t++) {// for das rodadas
//				for (int m = 0; m < metadeClubes; m++) {// for dos jogos
//					// Clube está de fora nessa rodada?
//					if (competidores.get(m) == null) {
//						continue;
//					}
//					// Teste para ajustar o mando de campo
//					if (m % 2 == 1 || t % 2 == 1 && m == 0) {
//						if (turno == 0) {
//							partida = new Partida();
//							competidoresEmCampo = new ArrayList<CompetidorEmCampo>();
//							// guardando o anfitrião
//							competidoresEmCampo.add(
//									new CompetidorEmCampo(partida, competidores.get(totalClubes - m - 1), 0, true));
//							// guardando o visitante
//							competidoresEmCampo.add(new CompetidorEmCampo(partida, competidores.get(m), 0, false));
//						} else {
//							partida = new Partida();
//							competidoresEmCampo = new ArrayList<CompetidorEmCampo>();
//							// guardando o anfitrião
//							competidoresEmCampo.add(new CompetidorEmCampo(partida, competidores.get(m), 0, true));
//							// guardando o visitante
//							competidoresEmCampo.add(
//									new CompetidorEmCampo(partida, competidores.get(totalClubes - m - 1), 0, false));
//						}
//					} else {
//
//						if (turno == 1) {
//							partida = new Partida();
//							competidoresEmCampo = new ArrayList<CompetidorEmCampo>();
//							// guardando o anfitrião
//							competidoresEmCampo.add(new CompetidorEmCampo(partida, competidores.get(m), 0, true));
//							// guardando o visitante
//							competidoresEmCampo.add(
//									new CompetidorEmCampo(partida, competidores.get(totalClubes - m - 1), 0, false));
//						} else {
//							partida = new Partida();
//							competidoresEmCampo = new ArrayList<CompetidorEmCampo>();
//							// guardando o anfitrião
//							competidoresEmCampo.add(
//									new CompetidorEmCampo(partida, competidores.get(totalClubes - m - 1), 0, true));
//							// guardando o visitante
//							competidoresEmCampo.add(new CompetidorEmCampo(partida, competidores.get(m), 0, false));
//						}
//					}
//				}
//
//				partida.setCompetidoresEmCampo(competidoresEmCampo);
//				// inserindo na lista
//
//				listaPartidas.add(partida);
//
//				// Gira os clubes no sentido horário, mantendo o primeiro no lugar
//				Competidor remove = competidores.remove(competidores.size() - 1);
//				competidores.add(1, remove);
//			}
//		}
//
//		return listaPartidas;
//	}

	/**
	 * Gera a tabela de classificação
	 * 
	 */
	public List<Classificacao> gerarClassificacao(List<Competidor> competidores, List<Partida> partidas) {

		List<Classificacao> listaClassificacao = new ArrayList<Classificacao>();

		int pontospossiveis = (competidores.size() - 1) * 3 * 2;

		for (Competidor competidor : competidores) {
			int jogos = 0;
			int vitorias = 0;
			int empates = 0;
			int derrotas = 0;
			int golspro = 0;
			int golscontra = 0;
			int pontos = 0;
			int saldo = 0;
			int aproveitamento = 0;

			for (Partida partida : partidas) {
				if (!partida.isEncerrada()) {
					System.out.println("Partida não acabou ainda");
					continue;
				}
				CompetidorEmCampo anfitriao = null;
				CompetidorEmCampo visitante = null;
				for (CompetidorEmCampo emCampo : partida.getCompetidoresEmCampo()) {
					if (emCampo.isJogaEmCasa()) {
						anfitriao = emCampo;
					} else {
						visitante = emCampo;
					}
				}

				if (anfitriao == null || visitante == null)
					continue;

				if (anfitriao.getCompetidor().getClube().getNome().equals(competidor.getClube().getNome())) {
					if (anfitriao.getGols() > visitante.getGols()) {
						vitorias++;
						pontos += 3;
					}

					if (anfitriao.getGols() == visitante.getGols()) {
						empates++;
						pontos++;
					}
					if (anfitriao.getGols() < visitante.getGols()) {
						derrotas++;
					}

					golspro += anfitriao.getGols();
					golscontra += visitante.getGols();
					jogos++;
				} else if (visitante.getCompetidor().getClube().getNome().equals(competidor.getClube().getNome())) {
					if (visitante.getGols() > anfitriao.getGols()) {
						vitorias++;
						pontos += 3;
					}
					if (visitante.getGols() == anfitriao.getGols()) {
						empates++;
						pontos++;
					}
					if (visitante.getGols() < anfitriao.getGols()) {
						derrotas++;
					}
					golspro += anfitriao.getGols();
					golscontra += visitante.getGols();
					jogos++;
				}
			}
			aproveitamento = (int) (((float) pontos / pontospossiveis) * 100);
			saldo = golspro - golscontra;
			listaClassificacao.add(new Classificacao(competidor.getClube().getNome(), pontos, jogos, vitorias, empates,
					derrotas, golspro, golscontra, saldo, aproveitamento));
		}
		Collections.sort(listaClassificacao);
		return listaClassificacao;
	}

}
