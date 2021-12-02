package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.vo.ChamadoVO;

public class MenuChamado {
	
	Scanner teclado = new Scanner(System.in);
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private static final int OPCAO_MENU_CADASTRAR_CHAMADO = 1;
	private static final int OPCAO_MENU_ATUALIZAR_CHAMADO = 2;
	private static final int OPCAO_MENU_EXCLUIR_CHAMADO = 3;
	private static final int OPCAO_MENU_CONSULTAR_CHAMADO = 4;
	private static final int OPCAO_MENU_CHAMADO_SAIR = 9;
	
	public void apresentarMenuChamado() {
		int opcao = this.apresentarOpcoesMenu();
		while(opcao != OPCAO_MENU_CHAMADO_SAIR) {
			switch(opcao) {
				case OPCAO_MENU_CADASTRAR_CHAMADO:{
					ChamadoVO chamadoVO = new ChamadoVO();
					this.cadastrarChamado(chamadoVO);
					break;
				}
				case OPCAO_MENU_ATUALIZAR_CHAMADO:{
					this.atualizarChamado();
					break;
				}
				case OPCAO_MENU_EXCLUIR_CHAMADO:{
					this.excluirChamado();
					break;
				}
				case OPCAO_MENU_CONSULTAR_CHAMADO:{
					this.consultarChamado();
					break;
				}
				default:{
					System.out.println("\nOpção Inválida!");
					break;
				}
			}
			opcao = this.apresentarOpcoesMenu();
		}
		
	}

	private int apresentarOpcoesMenu() {
		System.out.println("\n---- Sistema Socorro Desk ----");
		System.out.println("\n---- Menu Cadastro de Chamados ----");
		System.out.println(OPCAO_MENU_CADASTRAR_CHAMADO + " - Cadastrar Chamado");
		System.out.println(OPCAO_MENU_ATUALIZAR_CHAMADO + " - Consultar Chamado");
		System.out.println(OPCAO_MENU_EXCLUIR_CHAMADO + " - Atualizar Chamado");
		System.out.println(OPCAO_MENU_CONSULTAR_CHAMADO + " - Excluir Chamado");
		System.out.println(OPCAO_MENU_CHAMADO_SAIR + " - Voltar");
		System.out.print("\nDigite uma opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	private void cadastrarChamado(ChamadoVO chamadoVO) {
		System.out.println("\nTitulo do Chamado: ");
		chamadoVO.setTitulo(teclado.nextLine());
		System.out.println("Descrição: ");
		chamadoVO.setDescricao(teclado.nextLine());
		
		chamadoVO.setDataAbertura(LocalDate.now());
	}
	
	private void consultarChamado() {
		// TODO Auto-generated method stub
		
	}


	private void excluirChamado() {
		// TODO Auto-generated method stub
		
	}


	private void atualizarChamado() {
		// TODO Auto-generated method stub
		
	}
}
