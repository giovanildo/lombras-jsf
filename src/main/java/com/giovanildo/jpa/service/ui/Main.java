package com.giovanildo.jpa.service.ui;

import java.util.ArrayList;
import java.util.List;

import com.giovanildo.jpa.service.interfaces.CrudService;
import com.giovanildo.models.Clube;
import com.giovanildo.models.Competidor;
import com.giovanildo.models.EAtleta;
import com.giovanildo.models.Partida;
import com.giovanildo.models.Torneio;
import com.giovanildo.services.impl.ClubeService;
import com.giovanildo.services.impl.EatletaService;
import com.giovanildo.services.impl.PartidaService;
import com.giovanildo.services.impl.TorneioService;
import com.giovanildo.utils.Controller;

public class Main {

	public static CrudService<Torneio, Integer> torneioService = new TorneioService();
	public static CrudService<Partida, Integer> partidaService = new PartidaService();
	public static CrudService<EAtleta, Integer> eaService = new EatletaService();
	public static CrudService<Clube, Integer> clubeService = new ClubeService();

	public static void main(String[] args) {
		System.out.println("Testando JPA - competidor");

		adicionarEatletas();
		adicionarClubes();
		List<Competidor> competidores = adicionarTorneios();

		System.out.println(" --------------- Listando Dados ---------------------");

		listandoDados();

		for (Partida daVez : Controller.geraPartidas(competidores)) {
			partidaService.insert(daVez);
		}

		System.out.println("Inserido com sucesso!");

	}

	private static void listandoDados() {
		for (Clube daVez : clubeService.all()) {
			System.out.println(daVez.toString());
		}

		for (EAtleta daVez : eaService.all()) {
			System.out.println(daVez.toString());
		}

		for (Torneio daVez : torneioService.all()) {
			System.out.println(daVez.toString());
		}
	}

	private static List<Competidor> adicionarTorneios() {
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

		return competidores;
	}

	private static void adicionarEatletas() {
		eaService.insert(new EAtleta("Giovanildo"));
		eaService.insert(new EAtleta("Genilson"));
		eaService.insert(new EAtleta("Fabiano"));
		eaService.insert(new EAtleta("Zaldir"));

	}

	private static void adicionarClubes() {
		clubeService.insert(new Clube("Borrusia", "Alemanha"));
		clubeService.insert(new Clube("Real", "Espanha"));
		clubeService.insert(new Clube("Manchester", "Inglaterra"));
		clubeService.insert(new Clube("Porto", "Portugal"));
	}

}
