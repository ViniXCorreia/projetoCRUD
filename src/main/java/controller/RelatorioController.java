package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import model.bo.RelatorioBO;
import model.dto.RelatorioDTO;
import model.vo.UsuarioVO;

public class RelatorioController {

	public ArrayList<RelatorioDTO> rendimentoTodosTecnicosController() {
		RelatorioBO relatorioBO = new RelatorioBO();
		return relatorioBO.rendimentoTodosTecnicosBO();
	}

	public RelatorioDTO rendimentoIndividulTecnicoController(UsuarioVO usuarioVO, LocalDate dataAbertura, LocalDate dataFechamento) {
		RelatorioBO relatorioBO = new RelatorioBO();
		return relatorioBO.rendimentoIndividualTecnicoBO(usuarioVO, dataAbertura, dataFechamento);
	}

}
