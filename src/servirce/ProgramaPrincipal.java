package servirce;

import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;

import enuns.TipoSexo;
import model.Cliente;

public class ProgramaPrincipal {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);

		Persistencia persistencia = new Persistencia();

		MetodosUtilizadosNoPrograma metodos = new MetodosUtilizadosNoPrograma();

		System.out.print("-Informe o nome de um arquivo: ");
		String nomeArquivo = entrada.nextLine();

		CentralDeInformacoes centralDeInformacoes = persistencia.recupearCentral(nomeArquivo);
		
		String escolhaDoMenu;
		
		while (true) {
			
			metodos.menuInicial();
			escolhaDoMenu = entrada.nextLine().toUpperCase().trim();
			System.out.println("------------------------------------------------------");
			System.out.println();
			
			if(escolhaDoMenu.equals("S"))
				break;
			
			switch (escolhaDoMenu) {
			case "1":

				Cliente novoCliente = metodos.criaCliente();
				if(novoCliente != null) {
					
					if (centralDeInformacoes.adicionarCliente(novoCliente)) {
						persistencia.salvarCentral(centralDeInformacoes, nomeArquivo);
						System.out.println();
						System.out.print("Cliente cadastrado com sucesso\n");
					} else {
						System.out.println();
						System.out.print("Não foi possivel adicionar o cliente.\nJá existe um cliente com esse email.\n");
					}
				}
				
				break;
				
			case"2":
				
				metodos.listaClientes(centralDeInformacoes);
				break;
				
			case"3":
				
				System.out.print("Informe o Email do cliente: ");
				String email = entrada.nextLine();
				System.out.println();
				Cliente clienteEspecifico = centralDeInformacoes.recuperarClientePorEmail(email);
				if( clienteEspecifico != null) {
					metodos.exibeInformacoesDeUmClienteEspecifico(clienteEspecifico);
				}else {
					System.out.print("Cliente não encontrado\n");
				}
				break;
				
			case"4":
				System.out.print("Informe o nome do cliente Associado: ");
				String nomeDoClienteAssociado = entrada.nextLine();
				
				if(centralDeInformacoes.existeCliente(nomeDoClienteAssociado)) {
					Cliente cliente = metodos.pegarCliente(nomeDoClienteAssociado, centralDeInformacoes);
					if(metodos.criarNovoEvento(cliente, centralDeInformacoes)) {
						persistencia.salvarCentral(centralDeInformacoes, nomeArquivo);
					}
						
					
				}else {
					System.out.println("-Cliente não cadastrado");	
				}
				
				break;
				
			case"5":
				metodos.listarTodosOsEvento(centralDeInformacoes);
				break;
				
			case"6":
				metodos.ListarEventoDeUmCliente(centralDeInformacoes);
				break;
				
			case"7":
				System.out.print("-Informe um mes: ");
				String mes = entrada.nextLine().toUpperCase();
				GeradorDeRelatorio.obterProgramacaoDoMes(Month.valueOf(mes), centralDeInformacoes);
				break;
			default:
				
				System.out.println("-Opção invalida\n-Escolha novamente\n");
				break;
			

			}
		}
		
	}

}
