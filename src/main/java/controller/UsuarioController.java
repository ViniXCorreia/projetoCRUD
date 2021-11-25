package controller;

import java.util.ArrayList;

import model.bo.UsuarioBO;
import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVO;

public class UsuarioController {

	public UsuarioVO realizarLoginController(UsuarioVO usuarioVO) {
		UsuarioBO usuarioBO =  new UsuarioBO();
		return usuarioBO.realizarLoginBo(usuarioVO);
	}

	public ArrayList<TipoUsuarioVO> consultarTiposUsuarios() {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.consultarTipoUsuarioBO();
	}

	public UsuarioVO cadastrarUsuarioController(UsuarioVO usuarioVO) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.cadastrarUsuarioBO(usuarioVO);
	}
	
	public boolean excluirUsuarioController(UsuarioVO usarioVO) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.excluirUsuarioBO(usarioVO);
	}
	
	public boolean atualizarUsuarioCOntroller(UsuarioVO usuarioVO) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.atualizarUsuarioBO(usuarioVO);
	}

}
