package com.giovanildo.jpa.service.ui;

import java.util.List;
import java.util.Scanner;

import com.giovanildo.jpa.service.interfaces.CrudService;
import com.giovanildo.models.Clube;
import com.giovanildo.services.impl.ClubeService;


public class Main {
	private static Scanner SCANNER = new Scanner(System.in);

	public static void main(String[] args) {
		listarClubes();

		int opcao = 0;
		while (opcao != 6) {

			menu();

			opcao = SCANNER.nextInt();
			SCANNER.nextLine();
			switch (opcao) {
			case 1:
				listarClubes();
				break;
			case 2:
				inserirClube();
				break;
			case 3:
				atualizarClube();
				break;
			default:
				System.out.println("** opção inválida! **");
				break;
			}
		}

		System.out.println("Inté mais");

	}

	private static void atualizarClube() {
		System.out.println("\n Atualiza��o de Cadastro de Clube");
		System.out.println("Digite o ID da Clube a ser atualizada: ");
		int idClube = SCANNER.nextInt();
		SCANNER.nextLine();
		CrudService<Clube,Integer> ClubeService = new ClubeService();
		Clube ClubeAtual = ClubeService.byId(idClube);
		if(ClubeAtual !=null) {
			System.out.println("Clube encontrado: ");
			System.out.println(String.format("- nome %s", ClubeAtual.getNome()));
			System.out.println(String.format("- Nacionalidade %s", ClubeAtual.getNacionalidade()));
			System.out.print(" - Novo nome: ");
			ClubeAtual.setNome(SCANNER.nextLine());
			System.out.print(" - Nova Nacionalidade: ");
			ClubeAtual.setNacionalidade(SCANNER.nextLine());
			ClubeService.update(ClubeAtual);
			System.out.println("Dados de Clubes Atualizados");
		} else {
			System.out.println("N�o existem Clubes com esse id. Lascouse");
		}
	}

	private static void menu() {
		System.out.println("\n Escolha uma a��o: ");
		System.out.println("1, Listar Clubes");
		System.out.println("2, Inserir Clube");
		System.out.println("3, Atualizar Clube");
		System.out.println("4, Excluir Clube");
		System.out.println("5, Pesquisar Clube por nome");
		System.out.println("6, SAIR  \n");
		System.out.println("\n Sua op��o: ");

	}

	private static void inserirClube() {



		System.out.println(" ** Inclus�o de Clube");
		Clube novoClube = new Clube();
		System.out.println("Nome: ");
		novoClube.setNome(SCANNER.nextLine());
		System.out.println("Nacionalidade: ");
		novoClube.setNacionalidade(SCANNER.nextLine());

		CrudService<Clube, Integer> ClubeService = new ClubeService();

		ClubeService.insert(novoClube);

		System.out.println("Caboco" + novoClube.getNome() + "Cadastrado com sucesso");

	}

	private static void listarClubes() {
		System.out.println("---------------------- Listando por JPA ----------------------");
		System.out.println("---------------- Lista de Clubes Cadastradas-----------------/n");
		CrudService<Clube, Integer> ClubeService = new ClubeService();

		try {
			List<Clube> Clubes = ClubeService.all();
			Clubes.forEach(Clube -> System.out.println(String.format(" - (%d) %s %s ", Clube.getId(),
					Clube.getNome(), Clube.getNacionalidade())));
			if (Clubes.isEmpty()) {
				System.out.println("n�o tem registros");
			}
		} catch (Exception e) {
			System.out.println("Houve erro ao rodar o JPA " + e.getMessage());
		}
	}

}
