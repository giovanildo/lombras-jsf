package com.giovanildo.jpa.service.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.giovanildo.jpa.service.interfaces.CrudService;
import com.giovanildo.models.Clube;
import com.giovanildo.models.Competidor;
import com.giovanildo.models.EAtleta;
import com.giovanildo.models.Torneio;
import com.giovanildo.services.impl.ClubeService;
import com.giovanildo.services.impl.EatletaService;
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

//		Torneio torneio = new Torneio();
//		torneio.setDataTorneio(new Date());
//		torneio.setNome("Corona Varios");
//		torneio.setPorqueDoNome("ajasdjhasjhsdah");
//
//		Collection<Competidor> competidores = new ArrayList<Competidor>();
//
//		competidores.add(new Competidor(eaService.byString("Giovanildo"), torneio, clubeService.byId(1)));
//		competidores.add(new Competidor(eaService.byString("Zaldir"), torneio, clubeService.byId(2)));
//		competidores.add(new Competidor(eaService.byString("Fabiano"), torneio, clubeService.byId(3)));
//		torneio.setCompetidores(competidores);
//		
//		torneioService.insert(torneio);

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
		torneio.setPorqueDoNome("correndo atrás dos bichos na rua");

		Collection<Competidor> competidores = new ArrayList<>();

		competidores.add(new Competidor(torneio, eaService.byString("Giovanildo"), clubeService.byString("Porto")));
		competidores.add(new Competidor(torneio, eaService.byString("Genilson"), clubeService.byString("Manchester")));
		competidores.add(new Competidor(torneio, eaService.byString("Fabiano"), clubeService.byString("Real")));
		competidores.add(new Competidor(torneio, eaService.byString("Zaldir"), clubeService.byString("Baia")));

		torneio.setCompetidores(competidores);

		torneioService.insert(torneio);
		
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

}
