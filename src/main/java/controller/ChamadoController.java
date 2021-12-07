package controller;

import java.util.ArrayList;

import model.bo.ChamadoBO;
import model.vo.ChamadoVO;
import model.vo.UsuarioVO;

public class ChamadoController {

	public ChamadoVO cadastrarChamadoController(ChamadoVO chamadoVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.cadastrarChamadoBO(chamadoVO);
	}

	public boolean excluirChamadoController(ChamadoVO chamadoVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.excluirChamadoBO(chamadoVO);
	}

	public boolean atualizarChamadoController(ChamadoVO chamadoVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.atualizarChamadoBO(chamadoVO);
	}

	public ArrayList<ChamadoVO> consultarTodosChamadosController(UsuarioVO usuarioVO) {
		ChamadoBO chamadoBO= new ChamadoBO();
		return chamadoBO.consultarTodosChamadosBO(usuarioVO);
	}

	public ArrayList<ChamadoVO> consultarTodosChamadosAbertosController(UsuarioVO usuarioVO) {
		ChamadoBO chamadoBO= new ChamadoBO();
		return chamadoBO.consultarTodosChamadosAbertosBO(usuarioVO);
	}

	public ArrayList<ChamadoVO> consultarTodosChamadosFechadosController(UsuarioVO usuarioVO) {
		ChamadoBO chamadoBO= new ChamadoBO();
		return chamadoBO.consultarTodosChamadosFechadosBO(usuarioVO);
	}

	public ChamadoVO atenderChamadoController(ChamadoVO chamadoVO) {
		ChamadoBO chamadoBO= new ChamadoBO();
		return chamadoBO.atenderChamado(chamadoVO);
	}

	public ArrayList<ChamadoVO> listarChamadosAbertosController() {
		ChamadoBO chamadoBO= new ChamadoBO();
		return chamadoBO.listarChamadosAbertosBO();
	}

	public ArrayList<ChamadoVO> listarChamadosFechadosTecnicoController(UsuarioVO usuarioVO) {
		ChamadoBO chamadoBO= new ChamadoBO();
		return chamadoBO.listarChamadosFechadosTecnicoBO(usuarioVO);
	}
	
}
