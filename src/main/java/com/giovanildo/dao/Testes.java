package com.giovanildo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
import com.giovanildo.utils.JpaUtils;

public class Testes {
	public static CrudService<Torneio, Integer> torneioService = new TorneioService();
	public static CrudService<Partida, Integer> partidaService = new PartidaService();
	public static CrudService<EAtleta, Integer> eaService = new EatletaService();
	public static CrudService<Clube, Integer> clubeService = new ClubeService();
	
	
	public static void buscaEmCascata() {
		EntityManager em = JpaUtils.getEntityManager();
		
		String jpql = "select t from Torneio t join t.competidores c where c = :pCompetidor";
		
		Competidor competidor = new Competidor();
		competidor.setId(10);
		
		TypedQuery<Torneio> query = em.createQuery(jpql, Torneio.class);
		query.setParameter("pCompetidor", competidor);
		
		List<Torneio> lista = query.getResultList();
		
		for (Torneio daVez : lista) {
			System.out.println(daVez.getNome());
			System.out.println(daVez.getId());
			for (Competidor competidor1 : daVez.getCompetidores()) {
				System.out.println(competidor1.getClube());
			}
		}
		
	}
	
	public static void listandoDados() {
		for (Clube daVez : clubeService.all()) {
			System.out.println(daVez.toString());
		}

		for (EAtleta daVez : eaService.all()) {
			System.out.println(daVez.toString());
		}

		for (Torneio daVez : torneioService.all()) {
			System.out.println(daVez.toString());
			System.out.println(daVez.getId());
		}
	}

	public static List<Competidor> adicionarTorneios() {
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

	public static void adicionarEatletas() {
		eaService.insert(new EAtleta("Giovanildo"));
		eaService.insert(new EAtleta("Genilson"));
		eaService.insert(new EAtleta("Fabiano"));
		eaService.insert(new EAtleta("Zaldir"));

	}

	public static void adicionarClubes() {
		clubeService.insert(new Clube("Borrusia", "Alemanha"));
		clubeService.insert(new Clube("Real", "Espanha"));
		clubeService.insert(new Clube("Manchester", "Inglaterra"));
		clubeService.insert(new Clube("Porto", "Portugal"));
	}
}
