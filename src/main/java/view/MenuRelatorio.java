package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import controller.RelatorioController;
import model.dto.RelatorioDTO;
import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVO;

public class MenuRelatorio {
	
	Scanner teclado = new Scanner(System.in);
	
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private static final int OPCAO_RELATORIO_TODOS_TECNICOS = 1;
	private static final int OPCAO_RELATORIO_UM_TECNICO = 2;
	private static final int OPCAO_RELATORIO_SAIR = 9;
	private static ArrayList<Integer> opcoesMenuPermitidas;

	public void apresentarMenuRelatorio(UsuarioVO usuarioVO) {
		System.out.println("\n---- RELATÓRIO TECNICOS ----");
		int opcao = apresentarOpcoesMenu(usuarioVO);
		while(opcao != OPCAO_RELATORIO_SAIR) {
			switch(opcao) {
				case OPCAO_RELATORIO_TODOS_TECNICOS:{
					if(opcoesMenuPermitidas.contains(opcao)) {
						this.rendimentoTodosTecnicos();
					}else {
						System.out.println("Opção inválida!");
					}
					opcao = OPCAO_RELATORIO_SAIR;
					break;
				}
				case OPCAO_RELATORIO_UM_TECNICO:{
					this.rendimentoIndividualTecnico(usuarioVO);
					opcao = OPCAO_RELATORIO_SAIR;
					break;
				}
			}
			opcao = apresentarOpcoesMenu(usuarioVO);
		}
	}

	private void rendimentoTodosTecnicos() {
		RelatorioController relatorioController = new RelatorioController();
		ArrayList<RelatorioDTO> relatorioTecnicosDTO = relatorioController.rendimentoTodosTecnicosController();
		System.out.println("\n--------- RESULTADO DA CONSULTA ---------");
		System.out.printf("\n%10s  %10s  %10s  ", "NOME", "CHAMADOS ATENDIDOS", "MEDIA TEMPO RESOLUÇÃO");
		for(int i = 0; i < relatorioTecnicosDTO.size(); i++) {
			relatorioTecnicosDTO.get(i).imprimir();
		}
		System.out.println();
		
	}

	private void rendimentoIndividualTecnico(UsuarioVO usuarioVO) {
		RelatorioController relatorioController = new RelatorioController();
		System.out.println("Defina o começo do periodo que você deseja avaliar no formato dd/mm/aaaa:");
		LocalDate dataAbertura = LocalDate.parse(teclado.nextLine(), dataFormatter);
		System.out.println("Defina o fim do periodo que você deseja avaliar no formato dd/mm/aaaa:");
		LocalDate dataFechamento = LocalDate.parse(teclado.nextLine(), dataFormatter);
		RelatorioDTO relatorioTecnicoDTO = relatorioController.rendimentoIndividulTecnicoController(usuarioVO, dataAbertura, dataFechamento);
		System.out.println("\n--------- RESULTADO DA CONSULTA ---------");
		System.out.printf("\n%10s  %10s  %10s  ", "NOME", "CHAMADOS ATENDIDOS", "MEDIA TEMPO RESOLUÇÃO");
		relatorioTecnicoDTO.imprimir();
		System.out.println();
		
	}

	private int apresentarOpcoesMenu(UsuarioVO usuarioVO) {
		opcoesMenuPermitidas = new ArrayList<Integer>();
		System.out.println("\nInforme o tipo de relatorio a ser realizado:");
		if(usuarioVO.getTipoUsuario().equals(TipoUsuarioVO.ADMINISTRADOR)) {
			System.out.println(OPCAO_RELATORIO_TODOS_TECNICOS + " - Rendimento de Todos os técnicos");
			opcoesMenuPermitidas.add(OPCAO_RELATORIO_TODOS_TECNICOS);
		}
		opcoesMenuPermitidas.add(OPCAO_RELATORIO_UM_TECNICO);
		opcoesMenuPermitidas.add(OPCAO_RELATORIO_SAIR);
		System.out.println(OPCAO_RELATORIO_UM_TECNICO + " - Rendimento Individual");
		System.out.println(OPCAO_RELATORIO_SAIR + " - Voltar");
		System.out.print("\nDigite a Opção: ");
		return Integer.parseInt(teclado.nextLine());
	}

}
