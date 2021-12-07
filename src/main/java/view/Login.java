package view;

import java.util.Scanner;

import controller.UsuarioController;
import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVO;

public class Login {
	
	private static final int OPCAO_MENU_LOGIN = 1;
	private static final int OPCAO_MENU_CADASTRO = 2; 
	private static final int OPCAO_MENU_SAIR = 9; 
	
	Scanner scan = new Scanner(System.in);

	public void apresentarMenuLogin() {
		int opcao = this.apresentarOpcoesMenu();
		while (opcao != OPCAO_MENU_SAIR) {
			switch (opcao) {
				case OPCAO_MENU_LOGIN: {
					UsuarioVO usuarioVO = this.realizarLogin();
					if(usuarioVO.getIdUsuario() != 0 && usuarioVO.getDataExpiracao() == null) {
						System.out.println("Usuário Logado: " + usuarioVO.getLogin());
						System.out.println("Perfil: " + usuarioVO.getTipoUsuario());
						Menu menu = new Menu();
						menu.apresentarMenu(usuarioVO);
					}
					break;
				}
				case OPCAO_MENU_CADASTRO: {
					this.cadastrarNovoUsuario();
					break;
				}
				default: {
					System.out.println("Opção inválida!");
					break;
				}
			}
			opcao = this.apresentarOpcoesMenu();
		}

	}
	
	private int apresentarOpcoesMenu() {
		System.out.println("\n---- Sistema Socorro Desk ----");
		System.out.println("\nOpções: ");
		System.out.println(OPCAO_MENU_LOGIN + " - Entrar");
		System.out.println(OPCAO_MENU_CADASTRO + " - Criar Conta");
		System.out.println(OPCAO_MENU_SAIR + " - Sair");
		System.out.print("\nDigite uma opção: ");
		return Integer.parseInt(scan.nextLine());
	}

	private UsuarioVO realizarLogin() {
		UsuarioVO usuarioVO = new UsuarioVO();
		System.out.println("\n---- Informações ----");
		System.out.print("Login: ");
		usuarioVO.setLogin(scan.nextLine());
		System.out.print("Senha: ");
		usuarioVO.setSenha(scan.nextLine());
		
		if(usuarioVO.getLogin().isEmpty() || usuarioVO.getSenha().isEmpty()) {
			System.out.println("Campos Login e Senha obrigatórios!");
		}else {
			UsuarioController usuarioController = new UsuarioController();
			usuarioVO = usuarioController.realizarLoginController(usuarioVO);
			
			if(usuarioVO.getNome() == null || usuarioVO.getNome().isEmpty()) {
				System.out.println("Usuário Não Encontrado!");
			}
			if(usuarioVO.getDataExpiracao() != null) {
				System.out.println("Usuário Expirado!");
			}
		}
		return usuarioVO;
	}
	
	private void cadastrarNovoUsuario() {
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setTipoUsuario(TipoUsuarioVO.USUARIO);
		MenuUsuario menuUsuario = new MenuUsuario();
		menuUsuario.criarNovoUsuario(usuarioVO);
	}

	

}
