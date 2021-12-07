package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.vo.ChamadoVO;
import model.vo.UsuarioVO;

public class ChamadoDAO {
	
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public ChamadoVO cadastrarChamadoDAO(ChamadoVO chamadoVO) {
		
		String query = "INSERT INTO chamados(idusuario, idtecnico, titulo, descricao, dataabertura, solucao, datafechamento) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		
		try {
			pstmt.setInt(1, chamadoVO.getIdUsuario());
			pstmt.setInt(2, chamadoVO.getIdTecnico());
			pstmt.setString(3, chamadoVO.getTitulo());
			pstmt.setString(4, chamadoVO.getDescricao());
			pstmt.setObject(5, chamadoVO.getDataAbertura());
			pstmt.setString(6, chamadoVO.getSolucao());
			pstmt.setObject(7, chamadoVO.getDataFechamento());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			
			if(resultado.next()) {
				chamadoVO.setIdChamado(resultado.getInt(1));
			}
		}catch(SQLException e){
			System.out.println("Erro ao executar a query de cadastrar chamado!");
			System.out.println(e.getMessage());
		}finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		
		return chamadoVO;
	}

	public boolean verificarExistenciaPorIdChamadoDAO(int idChamado) {
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;
		
		String query = "SELECT idchamado FROM chamados WHERE idchamado = " + idChamado;
		
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				retorno = true;
			}
		}catch(SQLException e){
			System.out.println("Erro ao executar a query de consultar chamado por ID!");
			System.out.println(e.getMessage());
		}finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return retorno;
	}

	public boolean verificarDataFechamentoPorIdChamadoDAO(int idChamado) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;
		
		String query = "SELECT datafechamento FROM chamados WHERE idchamado = " + idChamado +
				" AND datafechamento IS NULL";
		
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				String dataFechamento = resultado.getString(1);
				if(dataFechamento != null) {
					retorno = true;
				}
			}
		}catch(SQLException e){
			System.out.println("Erro ao executar a query de consultar data de fechamento do chamado!");
			System.out.println(e.getMessage());
		}finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return retorno;
	}

	public boolean verificarProprietarioChamadoPorIdChamadoDAO(ChamadoVO chamadoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;
		
		String query = "SELECT idusuario FROM chamados WHERE idchamado = " + chamadoVO.getIdChamado() 
						+" AND idusuario = " + chamadoVO.getIdUsuario();
		
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				retorno = true;
			}
		}catch(SQLException e){
			System.out.println("Erro ao executar a query de consultar data de fechamento do chamado!");
			System.out.println(e.getMessage());
		}finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return retorno;
	}

	public boolean excluirChamadoDAO(int idChamado) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		
		String query = "DELETE FROM chamados WHERE idchamado = " + idChamado;
		
		try {
			if(stmt.executeUpdate(query) == 1) {
				retorno = true;
			}
		}catch(SQLException e){
			System.out.println("Erro ao executar a query de exclusão do chamado!");
			System.out.println(e.getMessage());
		}finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return retorno;

	}

	public boolean atualizarChamadoDAO(ChamadoVO chamadoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		
		String query = "UPDATE chamados SET titulo = '" + chamadoVO.getTitulo() 
				+ "', descricao = '" + chamadoVO.getDescricao()
				+ "', dataabertura = '" + chamadoVO.getDataAbertura()
				+ "' WHERE idchamado = " + chamadoVO.getIdChamado();
		
		try {
			if(stmt.executeUpdate(query) == 1) {
				retorno = true;
			}
		} catch(SQLException e){
			System.out.println("Erro ao executar a query de atualização do chamado.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	public ArrayList<ChamadoVO> consultarTodosChamadosDAO(UsuarioVO usuarioVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		
		ArrayList<ChamadoVO> listaChamadosVO = new ArrayList<ChamadoVO>();
		
		String query = "SELECT idchamado, idusuario, idtecnico, titulo, descricao, dataabertura, solucao, datafechamento "
				+ "FROM chamados WHERE idusuario = " + usuarioVO.getIdUsuario();
		
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				ChamadoVO chamadoVO = new ChamadoVO();
				chamadoVO.setIdChamado(Integer.parseInt(resultado.getString(1)));
				chamadoVO.setIdUsuario(Integer.parseInt(resultado.getString(2)));
				chamadoVO.setIdTecnico(Integer.parseInt(resultado.getString(3)));
				chamadoVO.setTitulo(resultado.getString(4));
				chamadoVO.setDescricao(resultado.getString(5));
				chamadoVO.setDataAbertura(LocalDate.parse(resultado.getString(6), dataFormatter));
				chamadoVO.setSolucao(resultado.getString(7));
				if(resultado.getString(8) != null) {
					chamadoVO.setDataFechamento(LocalDate.parse(resultado.getString(8), dataFormatter));
				}
				listaChamadosVO.add(chamadoVO);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de consulta de todos os chamados.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return listaChamadosVO;
	}

	public ArrayList<ChamadoVO> consultarTodosChamadosAbertosDAO(UsuarioVO usuarioVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		
		ArrayList<ChamadoVO> listaChamadosAbertosVO = new ArrayList<ChamadoVO>();
		
		String query = "SELECT idchamado, idusuario, idtecnico, titulo, descricao, dataabertura, solucao, datafechamento "
				+ "FROM chamados WHERE idusuario = " + usuarioVO.getIdUsuario() 
				+ " AND datafechamento IS NULL";
		
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				ChamadoVO chamadoVO = new ChamadoVO();
				chamadoVO.setIdChamado(Integer.parseInt(resultado.getString(1)));
				chamadoVO.setIdUsuario(Integer.parseInt(resultado.getString(2)));
				chamadoVO.setIdTecnico(Integer.parseInt(resultado.getString(3)));
				chamadoVO.setTitulo(resultado.getString(4));
				chamadoVO.setDescricao(resultado.getString(5));
				chamadoVO.setDataAbertura(LocalDate.parse(resultado.getString(6), dataFormatter));
				chamadoVO.setSolucao(resultado.getString(7));
				if(resultado.getString(8) != null) {
					chamadoVO.setDataFechamento(LocalDate.parse(resultado.getString(8), dataFormatter));
				}
				listaChamadosAbertosVO.add(chamadoVO);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de consulta dos chamados abertos.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return listaChamadosAbertosVO;
	}

	public ArrayList<ChamadoVO> consultarTodosChamadosFechadosDAO(UsuarioVO usuarioVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		
		ArrayList<ChamadoVO> listaChamadosFechadosVO = new ArrayList<ChamadoVO>();
		
		String query = "SELECT idchamado, idusuario, idtecnico, titulo, descricao, dataabertura, solucao, datafechamento "
				+ "FROM chamados WHERE idusuario = " + usuarioVO.getIdUsuario() 
				+ " AND datafechamento IS NOT NULL";
		
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				ChamadoVO chamadoVO = new ChamadoVO();
				chamadoVO.setIdChamado(Integer.parseInt(resultado.getString(1)));
				chamadoVO.setIdUsuario(Integer.parseInt(resultado.getString(2)));
				chamadoVO.setIdTecnico(Integer.parseInt(resultado.getString(3)));
				chamadoVO.setTitulo(resultado.getString(4));
				chamadoVO.setDescricao(resultado.getString(5));
				chamadoVO.setDataAbertura(LocalDate.parse(resultado.getString(6), dataFormatter));
				chamadoVO.setSolucao(resultado.getString(7));
				if(resultado.getString(8) != null) {
					chamadoVO.setDataFechamento(LocalDate.parse(resultado.getString(8), dataFormatter));
				}
				listaChamadosFechadosVO.add(chamadoVO);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de consulta dos chamados fechados.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return listaChamadosFechadosVO;
	}

	public ChamadoVO atenderChamado(ChamadoVO chamadoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ChamadoVO retorno = new ChamadoVO();
		
		String query = "UPDATE chamados SET idTecnico = " + chamadoVO.getIdTecnico() + ", solucao = '" + chamadoVO.getSolucao()
					+"', dataFechamento = '" + chamadoVO.getDataFechamento() + "' WHERE idChamado = " + chamadoVO.getIdChamado();
		
		try {
			if(stmt.executeUpdate(query) == 1) {
				retorno = this.consultarChamadoAtendido(chamadoVO.getIdChamado());
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de consulta de atender chamado.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	private ChamadoVO consultarChamadoAtendido(int idChamado) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		
		ChamadoVO chamadoVO = new ChamadoVO();
		
		String query = "SELECT idChamado, idUsuario, idTecnico, titulo, descricao, dataAbertura, solucao, dataFechamento "
				+ "FROM chamados WHERE idChamado = " + idChamado;
		
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				chamadoVO.setIdChamado(Integer.parseInt(resultado.getString(1)));
				chamadoVO.setIdUsuario(Integer.parseInt(resultado.getString(2)));
				chamadoVO.setIdTecnico(Integer.parseInt(resultado.getString(3)));
				chamadoVO.setTitulo(resultado.getString(4));
				chamadoVO.setDescricao(resultado.getString(5));
				chamadoVO.setDataAbertura(LocalDate.parse(resultado.getString(6), dataFormatter));
				chamadoVO.setSolucao(resultado.getString(7));
				chamadoVO.setDataFechamento(LocalDate.parse(resultado.getString(8), dataFormatter));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de consulta de consultar chamado atendido.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return chamadoVO;
	}

	public ArrayList<ChamadoVO> listarChamadosAbertosDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		
		ArrayList<ChamadoVO> listaChamadosAbertosVO = new ArrayList<ChamadoVO>();
		
		String query = "SELECT idChamado, idUsuario, titulo, descricao, dataAbertura FROM chamados WHERE datafechamento IS NULL";
		
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				ChamadoVO chamadoVO = new ChamadoVO();
				chamadoVO.setIdChamado(Integer.parseInt(resultado.getString(1)));
				chamadoVO.setIdUsuario(Integer.parseInt(resultado.getString(2)));
				chamadoVO.setTitulo(resultado.getString(3));
				chamadoVO.setDescricao(resultado.getString(4));
				chamadoVO.setDataAbertura(LocalDate.parse(resultado.getString(5), dataFormatter));
				chamadoVO.setSolucao("");
				listaChamadosAbertosVO.add(chamadoVO);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de consulta de listagem dos chamados abertos.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaChamadosAbertosVO;
	}

	public ArrayList<ChamadoVO> listarChamadosFechadosDAO(UsuarioVO usuarioVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		
		ArrayList<ChamadoVO> listaChamadosFechadosTecnicoVO = new ArrayList<ChamadoVO>();
		
		String query = "SELECT idChamado, idUsuario, idTecnico, titulo, descricao, dataAbertura, solucao, dataFechamento "
				+ "FROM chamados WHERE idTecnico = " + usuarioVO.getIdUsuario() + " AND dataFechamento IS NOT NULL";
		
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				ChamadoVO chamadoVO = new ChamadoVO();
				chamadoVO.setIdChamado(Integer.parseInt(resultado.getString(1)));
				chamadoVO.setIdUsuario(Integer.parseInt(resultado.getString(2)));
				chamadoVO.setIdTecnico(Integer.parseInt(resultado.getString(3)));
				chamadoVO.setTitulo(resultado.getString(4));
				chamadoVO.setDescricao(resultado.getString(5));
				chamadoVO.setDataAbertura(LocalDate.parse(resultado.getString(6), dataFormatter));
				chamadoVO.setSolucao(resultado.getString(7));
				chamadoVO.setDataFechamento(LocalDate.parse(resultado.getString(8), dataFormatter));
				listaChamadosFechadosTecnicoVO.add(chamadoVO);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de consulta dos chamados fechados.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaChamadosFechadosTecnicoVO;
	}
	
	
	
}
