package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import controller.ChamadoController;
import model.vo.ChamadoVO;
import model.vo.UsuarioVO;

public class MenuChamado {
	
	Scanner teclado = new Scanner(System.in);
	
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private static final int OPCAO_MENU_CADASTRAR_CHAMADO = 1;
	private static final int OPCAO_MENU_ATUALIZAR_CHAMADO = 2;
	private static final int OPCAO_MENU_EXCLUIR_CHAMADO = 3;
	private static final int OPCAO_MENU_CONSULTAR_CHAMADO = 4;
	private static final int OPCAO_MENU_CHAMADO_SAIR = 9;
	
	private static final int OPCAO_MENU_CONSULTAR_TODOS_CHAMADOS = 1;
	private static final int OPCAO_MENU_CONSULTAR_CHAMADOS_ABERTOS = 2;
	private static final int OPCAO_MENU_CONSULTAR_CHAMADOS_FECHADOS = 3;
	private static final int OPCAO_MENU_CONSULTAR_CHAMADO_SAIR = 9;
	
	public void apresentarMenuChamado(UsuarioVO usuarioVO) {
		int opcao = this.apresentarOpcoesMenu();
		while(opcao != OPCAO_MENU_CHAMADO_SAIR) {
			switch(opcao) {
				case OPCAO_MENU_CADASTRAR_CHAMADO:{
					this.cadastrarChamado(usuarioVO);
					break;
				}
				case OPCAO_MENU_ATUALIZAR_CHAMADO:{
					this.atualizarChamado(usuarioVO);
					break;
				}
				case OPCAO_MENU_EXCLUIR_CHAMADO:{
					this.excluirChamado(usuarioVO);
					break;
				}
				case OPCAO_MENU_CONSULTAR_CHAMADO:{
					this.consultarChamado(usuarioVO);
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
		System.out.println("\n---- Menu de Chamados ----");
		System.out.println(OPCAO_MENU_CADASTRAR_CHAMADO + " - Cadastrar Chamado");
		System.out.println(OPCAO_MENU_ATUALIZAR_CHAMADO + " - Atualizar Chamado");
		System.out.println(OPCAO_MENU_EXCLUIR_CHAMADO + " - Excluir Chamado");
		System.out.println(OPCAO_MENU_CONSULTAR_CHAMADO + " - Consultar Chamado");
		System.out.println(OPCAO_MENU_CHAMADO_SAIR + " - Voltar");
		System.out.print("\nDigite uma opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	private int apresentarOpcoesConsulta() {
		System.out.println("\nInforme o tipo de consulta a ser realizada");
		System.out.println(OPCAO_MENU_CONSULTAR_TODOS_CHAMADOS + " - Consultar todos os Chamados");
		System.out.println(OPCAO_MENU_CONSULTAR_CHAMADOS_ABERTOS + " - Consultar os Chamados Abertos");
		System.out.println(OPCAO_MENU_CONSULTAR_CHAMADOS_FECHADOS + " - Consultar os Chamados Fechados");
		System.out.println(OPCAO_MENU_CONSULTAR_CHAMADO_SAIR + " - Voltar");
		System.out.print("\nDigite uma opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	private void cadastrarChamado(UsuarioVO usuarioVO) {
		
		ChamadoVO chamadoVO = new ChamadoVO();
		System.out.println("\n---- Cadastrar Novo Chamado ----");
		chamadoVO.setIdUsuario(usuarioVO.getIdUsuario());
		System.out.println("\nTitulo do Chamado: ");
		chamadoVO.setTitulo(teclado.nextLine());
		System.out.println("Descrição: ");
		chamadoVO.setDescricao(teclado.nextLine());
		
		chamadoVO.setDataAbertura(LocalDate.now());
		
		if(this.validarCamposChamado(chamadoVO)) {
			ChamadoController chamadoController = new ChamadoController();
			chamadoVO = chamadoController.cadastrarChamadoController(chamadoVO);
			if(chamadoVO.getIdChamado() != 0) {
				System.out.println("Chamado Cadastrado com Sucesso");
			}else {
				System.out.println("Não foi possível cadastrar o chamado");
			}
			
		}
	}
	
	private boolean validarCamposChamado(ChamadoVO chamadoVO) {
		boolean resultado = true;
		System.out.println();
		if(chamadoVO.getDescricao() == null || chamadoVO.getDescricao().isEmpty()) {
			System.out.println("Campo Descrição é Obrigatório!");
			resultado = false;
		}
		if(chamadoVO.getTitulo() == null || chamadoVO.getTitulo().isEmpty()) {
			System.out.println("Campo Título é Obrigatório!");
			resultado = false;
		}
		if(chamadoVO.getDataAbertura() == null) {
			System.out.println("Campo de Data de abertura não preenchida");
			resultado =  false;
		}
		
		return resultado;
	}
	
	private void consultarChamado(UsuarioVO usuarioVO) {
		int opcao = this.apresentarOpcoesConsulta();
		ChamadoController chamadoController = new ChamadoController();
		while(opcao != OPCAO_MENU_CONSULTAR_CHAMADO_SAIR) {
			switch(opcao) {
				case OPCAO_MENU_CONSULTAR_TODOS_CHAMADOS:{
					opcao = OPCAO_MENU_CONSULTAR_CHAMADO_SAIR;
					ArrayList<ChamadoVO> listaChamadosVO = chamadoController.consultarTodosChamadosController(usuarioVO);
					System.out.println("\n-------- RESULTADO DA CONSULTA --------");
					System.out.printf("\n%3s %-3s %-3s %-20s %-20s %-15s %-20s %-15s  ", "IDCHAMADO", "IDUSUARIO",
							"IDTECNICO", "TITULO", "DESCRICAO", "DATA ABERTURA", "SOLUCAO", "DATAFECHAMENTO");
					for(int i = 0; i<listaChamadosVO.size(); i++) {
						listaChamadosVO.get(i).imprimir();
					}
					System.out.println();
					break;
				}
				case OPCAO_MENU_CONSULTAR_CHAMADOS_ABERTOS:{
					opcao = OPCAO_MENU_CONSULTAR_CHAMADO_SAIR;
					ArrayList<ChamadoVO> listaChamadosAbertosVO = chamadoController.consultarTodosChamadosAbertosController(usuarioVO);
					System.out.println("\n-------- RESULTADO DA CONSULTA --------");
					System.out.printf("\n%3s %-3s %-3s %-20s %-20s %-15s %-20s %-15s  ", "IDCHAMADO", "IDUSUARIO",
							"IDTECNICO", "TITULO", "DESCRICAO", "DATA ABERTURA", "SOLUCAO", "DATAFECHAMENTO");
					for(int i = 0; i<listaChamadosAbertosVO.size(); i++) {
						listaChamadosAbertosVO.get(i).imprimir();
					}
					System.out.println();
					break;
				}
				case OPCAO_MENU_CONSULTAR_CHAMADOS_FECHADOS:{
					opcao = OPCAO_MENU_CONSULTAR_CHAMADO_SAIR;
					ArrayList<ChamadoVO> listaChamadosFechadosVO = chamadoController.consultarTodosChamadosFechadosController(usuarioVO);
					System.out.println("\n-------- RESULTADO DA CONSULTA --------");
					System.out.printf("\n%3s %-3s %-3s %-20s %-20s %-15s %-20s %-15s  ", "IDCHAMADO", "IDUSUARIO",
							"IDTECNICO", "TITULO", "DESCRICAO", "DATA ABERTURA", "SOLUCAO", "DATAFECHAMENTO");
					for(int i = 0; i<listaChamadosFechadosVO.size(); i++) {
						listaChamadosFechadosVO.get(i).imprimir();
					}
					System.out.println();
					break;
				}
				default:{
					System.out.println("\nOpção Inválida");
					opcao = this.apresentarOpcoesConsulta();
				}
			}
		}
	}


	private void excluirChamado(UsuarioVO usuarioVO) {
		ChamadoVO chamadoVO = new ChamadoVO();
		chamadoVO.setIdUsuario(usuarioVO.getIdUsuario());
		System.out.println("\n---- Excluir Chamado ----");
		System.out.print("\nDigite o ID do Chamado a ser excluído: ");
		chamadoVO.setIdChamado(Integer.parseInt(teclado.nextLine()));
		
		ChamadoController chamadoController = new ChamadoController();
		boolean resultado = chamadoController.excluirChamadoController(chamadoVO);
		
		if(resultado) {
			System.out.println("Chamado excluido com sucesso");
		}else {
			System.out.println("Não foi possível excluir o chamado");
		}
		
	}


	private void atualizarChamado(UsuarioVO usuarioVO) {
		ChamadoVO chamadoVO = new ChamadoVO();
		chamadoVO.setIdUsuario(usuarioVO.getIdUsuario());
		System.out.println("\n---- Atualizar Chamado ----");
		System.out.print("\nInforme o ID do chamado: ");
		chamadoVO.setIdChamado(Integer.parseInt(teclado.nextLine()));
		System.out.print("\nNovo título do chamado: ");
		chamadoVO.setTitulo(teclado.nextLine());
		System.out.print("\nNova descrição do chamado: ");
		chamadoVO.setDescricao(teclado.nextLine());
		System.out.print("\nNova data de abertura do chamado: ");
		chamadoVO.setDataAbertura(LocalDate.parse(teclado.nextLine(), dataFormatter));
		if(this.validarCamposChamado(chamadoVO)) {
			ChamadoController chamadoController = new ChamadoController();
			boolean resultado = chamadoController.atualizarChamadoController(chamadoVO);
			if(resultado) {
				System.out.println("Chamado Atualizado Com Sucesso!");
			}else {
				System.out.println("Não foi possível atualizar o chamado!");
			}
		}
	}
}
