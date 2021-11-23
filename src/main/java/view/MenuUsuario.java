package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import controller.UsuarioController;
import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVO;

public class MenuUsuario {

	Scanner teclado = new Scanner(System.in);

	private static final int OPCAO_MENU_CADASTRAR_USUARIO = 1;
	private static final int OPCAO_MENU_CONSULTAR_USUARIO = 2;
	private static final int OPCAO_MENU_ATUALIZAR_USUARIO = 3;
	private static final int OPCAO_MENU_EXCLUIR_USUARIO = 4;
	private static final int OPCAO_MENU_USUARIO_SAIR = 9;

	public void apresentarMenuUsuario() {
		int opcao = this.apresentarOpcoesMenu();
		while (opcao != OPCAO_MENU_USUARIO_SAIR) {
			switch (opcao) {
				case OPCAO_MENU_CADASTRAR_USUARIO: {
					UsuarioVO usuarioVO = new UsuarioVO();
					this.cadastrarUsuario(usuarioVO);
					break;
				}
				case OPCAO_MENU_CONSULTAR_USUARIO: {
					this.consultarUsuario();
					break;
				}
				case OPCAO_MENU_ATUALIZAR_USUARIO: {
					this.atualizarUsuario();
					break;
				}
				case OPCAO_MENU_EXCLUIR_USUARIO: {
					this.excluirUsuario();
					break;
				}
				default: {
					System.out.println("\nOpcao inválida!");
				}
			}
			opcao = this.apresentarOpcoesMenu();
		}
	}

	private int apresentarOpcoesMenu() {
		System.out.println("\n---- Sistema Socorro Desk ----");
		System.out.println("\n---- Menu Cadastro de Usuários ----");
		System.out.println(OPCAO_MENU_CADASTRAR_USUARIO + " - Cadastrar Usuário");
		System.out.println(OPCAO_MENU_CONSULTAR_USUARIO + " - Consultar Usuário");
		System.out.println(OPCAO_MENU_ATUALIZAR_USUARIO + " - Atualizar Usuário");
		System.out.println(OPCAO_MENU_EXCLUIR_USUARIO + " - Excluir Usuário");
		System.out.println(OPCAO_MENU_USUARIO_SAIR + " - Voltar");
		System.out.print("\nDigite uma opção: ");
		return Integer.parseInt(teclado.nextLine());
	}

	public void criarNovoUsuario(UsuarioVO usuarioVO) {
		this.cadastrarUsuario(usuarioVO);

	}

	private void excluirUsuario() {
		// TODO Auto-generated method stub

	}

	private void atualizarUsuario() {
		// TODO Auto-generated method stub

	}

	private void consultarUsuario() {
		// TODO Auto-generated method stub

	}

	private void cadastrarUsuario(UsuarioVO usuarioVO) {
		if(usuarioVO.getTipoUsuario() == null) {
			do {
				usuarioVO.setTipoUsuario(TipoUsuarioVO.getTipoUsuarioVOPorValor(this.apresentarOpcoesTipoUsuario()));
			}while(usuarioVO.getTipoUsuario() == null);
		}
		System.out.print("\nDigite o nome: ");
		usuarioVO.setNome(teclado.nextLine());
		System.out.print("\nDigite o cpf: ");
		usuarioVO.setCpf(teclado.nextLine());
		System.out.print("\nDigite o email: ");
		usuarioVO.setEmail(teclado.nextLine());
		
		usuarioVO.setDataCadastro(LocalDate.now());
		
		System.out.print("\nDigite o Login: ");
		usuarioVO.setLogin(teclado.nextLine());
		System.out.print("\nDigite o Senha: ");
		usuarioVO.setSenha(teclado.nextLine());
		
		if(this.validarCamposCadastro(usuarioVO)) {
			UsuarioController usuarioCOntroller = new UsuarioController();
			usuarioVO = usuarioCOntroller.cadastrarUsuarioController(usuarioVO);
			if(usuarioVO.getIdUsuario() != 0) {
				System.out.println("Usuário cadastrado com sucesso!");
			}else {
				System.out.println("Não foi possível cadastrar o usuário!");
			}
		}

	}

	private boolean validarCamposCadastro(UsuarioVO usuarioVO) {
		boolean resultado = true;
		System.out.println();
		if(usuarioVO.getNome() == null && usuarioVO.getNome().isEmpty()) {
			System.out.println("O campo nome é obrigatorio!");
			resultado = false;
		}
		if(usuarioVO.getCpf() == null && usuarioVO.getCpf().isEmpty()) {
			System.out.println("O campo CPF é obrigatorio!");
			resultado = false;
		}
		if(usuarioVO.getEmail() == null && usuarioVO.getEmail().isEmpty()) {
			System.out.println("O campo Email é obrigatorio!");
			resultado = false;
		}
		if(usuarioVO.getLogin() == null && usuarioVO.getLogin().isEmpty()) {
			System.out.println("O campo Login é obrigatorio!");
			resultado = false;
		}
		if(usuarioVO.getSenha() == null && usuarioVO.getSenha().isEmpty()) {
			System.out.println("O campo Senha é obrigatorio!");
			resultado = false;
		}
		return resultado;
	}

	private int apresentarOpcoesTipoUsuario() {
		UsuarioController usuarioController = new UsuarioController();
		ArrayList<TipoUsuarioVO> listaTipoUsuarioVO = usuarioController.consultarTiposUsuarios();
		System.out.println("\n---- Tipos de Usuários ----");
		System.out.println("Opções: ");
		for(int i = 0; i < listaTipoUsuarioVO.size(); i++) {
			System.out.println(listaTipoUsuarioVO.get(i).getValor() + " - " + listaTipoUsuarioVO.get(i));
		}
		System.out.print("\nDigitie uma opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	


}
