package model.bo;

import java.util.ArrayList;

import model.dao.ChamadoDAO;
import model.vo.ChamadoVO;
import model.vo.UsuarioVO;

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
			if(chamadoDAO.verificarDataFechamentoPorIdChamadoDAO(chamadoVO.getIdChamado())) {
				if(chamadoDAO.verificarProprietarioChamadoPorIdChamadoDAO(chamadoVO)) {
					resultado = chamadoDAO.excluirChamadoDAO(chamadoVO.getIdChamado());
				} else {
					System.out.println("Esse chamado não pertence a este usuário!");
				}
			} else {
				System.out.println("Chamado Já se encontra Fechado!");
			}
		} else {
			System.out.println("\nChamado não existe na base de dados!");
		}
		return resultado;
	}

	public boolean atualizarChamadoBO(ChamadoVO chamadoVO) {
		boolean resultado = false;
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		if(chamadoDAO.verificarExistenciaPorIdChamadoDAO(chamadoVO.getIdChamado())) {
			if(chamadoDAO.verificarDataFechamentoPorIdChamadoDAO(chamadoVO.getIdChamado())) {
				if(chamadoDAO.verificarProprietarioChamadoPorIdChamadoDAO(chamadoVO)) {
					resultado = chamadoDAO.atualizarChamadoDAO(chamadoVO);
				} else {
					System.out.println("Esse chamado não pertence a este usuário!");
				}
			} else {
				System.out.println("Chamado Já se encontra Fechado!");
			}
		} else {
			System.out.println("\nChamado não existe na base de dados!");
		}
		return resultado;
	}

	public ArrayList<ChamadoVO> consultarTodosChamadosBO(UsuarioVO usuarioVO) {
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		ArrayList<ChamadoVO> listaChamadosVO = chamadoDAO.consultarTodosChamadosDAO(usuarioVO);
		if(listaChamadosVO.isEmpty()) {
			System.out.println("Nenhum Chamado Cadastrado");
		}
		return listaChamadosVO;
	}

	public ArrayList<ChamadoVO> consultarTodosChamadosAbertosBO(UsuarioVO usuarioVO) {
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		ArrayList<ChamadoVO> listaChamadosAbertosVO = chamadoDAO.consultarTodosChamadosAbertosDAO(usuarioVO);
		if(listaChamadosAbertosVO.isEmpty()) {
			System.out.println("Nenhum Chamado Aberto");
		}
		return listaChamadosAbertosVO;
	}

	public ArrayList<ChamadoVO> consultarTodosChamadosFechadosBO(UsuarioVO usuarioVO) {
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		ArrayList<ChamadoVO> listaChamadosFechadosVO = chamadoDAO.consultarTodosChamadosFechadosDAO(usuarioVO);
		if(listaChamadosFechadosVO.isEmpty()) {
			System.out.println("Nenhum Chamado Encerrado até o momento");
		}
		return listaChamadosFechadosVO;
	}

	public ChamadoVO atenderChamado(ChamadoVO chamadoVO) {
		ChamadoVO retorno = new ChamadoVO();
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		if(chamadoDAO.verificarExistenciaPorIdChamadoDAO(chamadoVO.getIdChamado())) {
			if(chamadoDAO.verificarDataFechamentoPorIdChamadoDAO(chamadoVO.getIdChamado())) {
				System.out.println("\nChamado já se encontra fechado na base de dados");
			} else {
				retorno = chamadoDAO.atenderChamado(chamadoVO); 
			}
		} else {
			System.out.println("\nChamado não existe na base de dados");
		}
		return retorno;
	}

	public ArrayList<ChamadoVO> listarChamadosAbertosBO() {
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		ArrayList<ChamadoVO> chamadosAbertosVO = chamadoDAO.listarChamadosAbertosDAO();
		if(chamadosAbertosVO.isEmpty()) {
			System.out.println("Não existem chamados abertos!");
		}
		return chamadosAbertosVO;
	}

	public ArrayList<ChamadoVO> listarChamadosFechadosTecnicoBO(UsuarioVO usuarioVO) {
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		ArrayList<ChamadoVO> chamadosFechadosVO = chamadoDAO.listarChamadosFechadosDAO(usuarioVO);
		if(chamadosFechadosVO.isEmpty()) {
			System.out.println("Lista de chamados vazia!");
		}
		return chamadosFechadosVO;
	}

}
