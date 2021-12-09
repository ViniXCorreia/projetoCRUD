package model.bo;

import java.time.LocalDate;
import java.util.ArrayList;

import model.dao.RelatorioDAO;
import model.dto.RelatorioDTO;
import model.vo.UsuarioVO;

public class RelatorioBO {

	public ArrayList<RelatorioDTO> rendimentoTodosTecnicosBO() {
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		return relatorioDAO.rendimentoTodosTecnicosDAO();
	}

	public RelatorioDTO rendimentoIndividualTecnicoBO(UsuarioVO usuarioVO, LocalDate dataAbertura, LocalDate dataFechamento) {
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		return relatorioDAO.rendimentoIndividualTecnicoDAO(usuarioVO, dataAbertura, dataFechamento);
	}

}
