package model.bo;

import java.util.ArrayList;

import model.dao.ChamadoDAO;
import model.vo.ChamadoVO;

public class ChamadoBO {
	
	public ChamadoVO cadastrarChamadoBO(ChamadoVO chamadoVO) {
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		chamadoVO = chamadoDAO.cadastrarChamadoDAO(chamadoVO);
		return chamadoVO;
	}

	public boolean excluirChamadoBO(ChamadoVO chamadoVO) {
		boolean resultado = false;
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		if(chamadoDAO.verificarExistenciaPorIdChamadoDAO(chamadoVO.getIdChamado())) {
			if(chamadoDAO.verificarDataFechamentoPorIdChamadoDAO(chamadoVO)) {
				if(chamadoDAO.verificarProprietarioChamadoPorIdChamadoDAO(chamadoVO)) {
					resultado = chamadoDAO.excluirChamadoDAO(chamadoVO);
				}
			}
		}
		return resultado;
	}

	public boolean atualizarChamadoBO(ChamadoVO chamadoVO) {
		boolean resultado = false;
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		if(chamadoDAO.verificarExistenciaPorIdChamadoDAO(chamadoVO.getIdChamado())) {
			if(chamadoDAO.verificarDataFechamentoPorIdChamadoDAO(chamadoVO)) {
				if(chamadoDAO.verificarProprietarioChamadoPorIdChamadoDAO(chamadoVO)) {
					resultado = chamadoDAO.atualizarChamadoDAO(chamadoVO);
				}
			}
		}
		return resultado;
	}

	public ArrayList<ChamadoVO> consultarTodosChamadosBO() {
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		ArrayList<ChamadoVO> listaChamadosVO = chamadoDAO.consultarTodosChamadosDAO();
		if(listaChamadosVO.isEmpty()) {
			System.out.println("Nenhum Chamado Cadastrado");
		}
		return listaChamadosVO;
	}

	public ArrayList<ChamadoVO> consultarTodosChamadosAbertosBO() {
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		ArrayList<ChamadoVO> listaChamadosAbertosVO = chamadoDAO.consultarTodosChamadosAbertosDAO();
		if(listaChamadosAbertosVO.isEmpty()) {
			System.out.println("Nenhum Chamado Aberto");
		}
		return listaChamadosAbertosVO;
	}

	public ArrayList<ChamadoVO> consultarTodosChamadosFechadosBO() {
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		ArrayList<ChamadoVO> listaChamadosFechadosVO = chamadoDAO.consultarTodosChamadosFechadosDAO();
		if(listaChamadosFechadosVO.isEmpty()) {
			System.out.println("Nenhum Chamado Encerrado até o momento");
		}
		return listaChamadosFechadosVO;
	}

}
