package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import controller.ChamadoController;
import model.vo.ChamadoVO;
import model.vo.UsuarioVO;

public class MenuAtendimento {
	
	Scanner teclado = new Scanner(System.in);
	
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private static final int OPCAO_MENU_LISTAR_CHAMADO = 1;
	private static final int OPCAO_MENU_ATENDER_CHAMADO = 2;
	private static final int OPCAO_MENU_CHAMADO_SAIR = 9;
	
	private static final int OPCAO_MENU_LISTAR_CHAMADOS_ABERTOS = 1;
	private static final int OPCAO_MENU_LISTAR_CHAMADOS_FECHADOS = 2;
	private static final int OPCAO_MENU_LISTAR_CHAMADOS_SAIR = 9;
	

	public void apresentarMenuAtendimento(UsuarioVO usuarioVO) {
		int opcao = apresentarOpcoesMenu();
		while(opcao != OPCAO_MENU_CHAMADO_SAIR) {
			switch(opcao) {
				case OPCAO_MENU_LISTAR_CHAMADO:{
					this.listarChamados(usuarioVO);
					break;
				}
				case OPCAO_MENU_ATENDER_CHAMADO:{
					this.atenderChamado(usuarioVO);
					break;
				}
				default:{
					System.out.println("\nOpção Inválida");
				}
			}
			opcao = apresentarOpcoesMenu();
		}
		
		
	}


	private void atenderChamado(UsuarioVO usuarioVO) {
		ChamadoVO chamadoVO = new ChamadoVO();
		chamadoVO.setIdTecnico(usuarioVO.getIdUsuario());
		System.out.print("\nDigite o código do chamado: ");
		chamadoVO.setIdChamado(Integer.parseInt(teclado.nextLine()));
		System.out.print("Digite a solução: ");
		chamadoVO.setSolucao(teclado.nextLine());
		chamadoVO.setDataFechamento(LocalDate.now());
		
		if(chamadoVO.getIdChamado() == 0 || chamadoVO.getSolucao().isEmpty()) {
			System.out.println("Os campos código do chamado e solução são obrigatórios!");
		} else {
			ChamadoController chamadoController = new ChamadoController();
			chamadoVO = chamadoController.atenderChamadoController(chamadoVO);
			if(chamadoVO.getDataFechamento() != null) {
				System.out.println("Chamado fechado com sucesso!");
			} else {
				System.out.println("Não foi possível fechar o chamado!");
			}
		}
	}


	private void listarChamados(UsuarioVO usuarioVO) {
		int opcao = apresentarOpcoesConsulta();
		ChamadoController chamadoController = new ChamadoController();
		while(opcao != OPCAO_MENU_LISTAR_CHAMADOS_SAIR) {
			switch(opcao) {
				case OPCAO_MENU_LISTAR_CHAMADOS_ABERTOS:{
					opcao = OPCAO_MENU_LISTAR_CHAMADOS_SAIR;
					ArrayList<ChamadoVO> listaChamadosVO = chamadoController.listarChamadosAbertosController();
					System.out.println("\n--------- RESULTADO DA CONSULTA ---------");
					System.out.printf("\n%10s  %10s  %10s  %-30s  %-50s  %-15s  %-30s  %-15s  ", "ID CHAMADO",
							"ID USUÁRIO", "ID TÉCNICO", "TÍTULO", "DESCRIÇÃO", "DATA ABERTURA", "SOLUCAO", "DATA FECHAMENTO");
					for(int i = 0; i <listaChamadosVO.size(); i++) {
						listaChamadosVO.get(i).imprimir();
					}
					System.out.println();
					break;
				}
				case OPCAO_MENU_LISTAR_CHAMADOS_FECHADOS:{
					opcao = OPCAO_MENU_LISTAR_CHAMADOS_SAIR;
					ArrayList<ChamadoVO> listaChamadosVO = chamadoController.listarChamadosFechadosTecnicoController(usuarioVO);
					System.out.println("\n--------- RESULTADO DA CONSULTA ---------");
					System.out.printf("\n%10s  %10s  %10s  %-30s  %-50s  %-15s  %-30s  %-15s  ", "ID CHAMADO",
							"ID USUÁRIO", "ID TÉCNICO", "TÍTULO", "DESCRIÇÃO", "DATA ABERTURA", "SOLUCAO", "DATA FECHAMENTO");
					for(int i = 0; i <listaChamadosVO.size(); i++) {
						listaChamadosVO.get(i).imprimir();
					}
					System.out.println();
					break;
				}
				default:{
					System.out.println("Opção inválida!");
					break;
				}
			}
		}
	}


	private int apresentarOpcoesConsulta() {
		System.out.println("\nInforme o tipo de consulta a ser realizada:");
		System.out.println(OPCAO_MENU_LISTAR_CHAMADOS_ABERTOS + " - Listar todos os Chamados Abertos");
		System.out.println(OPCAO_MENU_LISTAR_CHAMADOS_FECHADOS + " - Listar todos os Chamados Fechados");
		System.out.println(OPCAO_MENU_LISTAR_CHAMADOS_SAIR + " - Voltar");
		System.out.print("\nDigite a Opção: ");
		return Integer.parseInt(teclado.nextLine());
	}


	private int apresentarOpcoesMenu() {
		System.out.println("\n---- Sistema Socorro Desk ----");
		System.out.println("\n---- Menu Atendimento de Chamados ----");
		System.out.println("\nOpções: ");
		System.out.println(OPCAO_MENU_LISTAR_CHAMADO + " - Listar Chamados");
		System.out.println(OPCAO_MENU_ATENDER_CHAMADO + " - Atender Chamado");
		System.out.println(OPCAO_MENU_LISTAR_CHAMADO + " - Voltar");
		System.out.print("Digite uma opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	
}
