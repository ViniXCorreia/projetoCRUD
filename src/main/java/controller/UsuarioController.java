package controller;

import model.bo.UsuarioBO;
import model.vo.UsuarioVO;

public class UsuarioController {

	public UsuarioVO realizarLoginController(UsuarioVO usuarioVO) {
		UsuarioBO usuarioBO =  new UsuarioBO();
		return usuarioBO.realizarLoginBo(usuarioVO);
	}

}
