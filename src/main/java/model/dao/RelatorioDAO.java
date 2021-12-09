package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import model.dto.RelatorioDTO;
import model.vo.UsuarioVO;

public class RelatorioDAO {

	public ArrayList<RelatorioDTO> rendimentoTodosTecnicosDAO() {
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		
		ArrayList<RelatorioDTO> rendimentoTecnicosDTO = new ArrayList<RelatorioDTO>();
		
		String query = "SELECT u.nome, count(c.idchamado), avg(datediff(c.datafechamento, c.dataabertura)) "
				+ "FROM chamados as c INNER JOIN usuario as u ON c.idtecnico = u.idusuario GROUP BY u.nome";
		
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				RelatorioDTO relatorioDTO = new RelatorioDTO();
				relatorioDTO.setNome(resultado.getString(1));
				relatorioDTO.setQtdChamados(Integer.parseInt(resultado.getString(2)));
				relatorioDTO.setMediaResolução(Double.parseDouble(resultado.getString(3)));
				rendimentoTecnicosDTO.add(relatorioDTO);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de consulta de rendimento de todos os técnicos.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return rendimentoTecnicosDTO;
	}

	public RelatorioDTO rendimentoIndividualTecnicoDAO(UsuarioVO usuarioVO, LocalDate dataAbertura, LocalDate dataFechamento) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
				
		String query = "SELECT u.nome, count(c.idchamado), avg(datediff(c.datafechamento, c.dataabertura)) "
				+ "FROM chamados as c INNER JOIN usuario as u ON c.idtecnico = u.idusuario " 
				+ "WHERE c.idtecnico = " + usuarioVO.getIdUsuario()
				+ " AND c.datafechamento BETWEEN '" + dataAbertura + "' AND '" + dataFechamento + "'"
				+ " GROUP BY u.nome";
		
		RelatorioDTO relatorioDTO = new RelatorioDTO();
		
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				relatorioDTO.setNome(resultado.getString(1));
				relatorioDTO.setQtdChamados(Integer.parseInt(resultado.getString(2)));
				relatorioDTO.setMediaResolução(Double.parseDouble(resultado.getString(3)));
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao executar a query de consulta de rendimento de todos os técnicos.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return relatorioDTO;
	}
	
}
