package controller;

import java.util.ArrayList;

import model.bo.ChamadoBO;
import model.vo.ChamadoVO;

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

	public ArrayList<ChamadoVO> consultarTodosChamadosController() {
		ChamadoBO chamadoBO= new ChamadoBO();
		return chamadoBO.consultarTodosChamadosBO();
	}

	public ArrayList<ChamadoVO> consultarTodosChamadosAbertosController() {
		ChamadoBO chamadoBO= new ChamadoBO();
		return chamadoBO.consultarTodosChamadosAbertosBO();
	}

	public ArrayList<ChamadoVO> consultarTodosChamadosFechadosController() {
		ChamadoBO chamadoBO= new ChamadoBO();
		return chamadoBO.consultarTodosChamadosFechadosBO();
	}
	
}
