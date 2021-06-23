package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.Retrait;

public class RetraitDAOJdbcImpl implements RetraitDAO {

	//insert
		private final String INSERT_RETRAIT=				"INSERT INTO RETRAITS VALUES (?,?,?,?);";
		
	//update
		private final String UPDATE_RETRAIT=				"UPDATE RETRAITS SET rue=?,code_postal=?,ville=?"
															+ " WHERE no_article=?;";
	
	//delete
		private final String DELETE_RETRAIT=				"DELETE FROM RETRAITS WHERE no_article=?;";
		
	//SELECT
		private final String FIND_RETRAIT_BY_ID_ARTICLE= 	"SELECT rue,code_postal,ville FROM RETRAITS WHERE no_article=?;";
	
	@Override
	public void addRetrait(Retrait retrait, int noArticle) throws BusinessException {
		String rue = retrait.getRue();
		String codePostal = retrait.getCodePostal();
		String ville = retrait.getVille();
		
			try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(INSERT_RETRAIT))
				{
					stmt.setInt(1, noArticle);
					stmt.setString(2, rue);
					stmt.setString(3, codePostal);
					stmt.setString(4, ville);
					
						stmt.executeUpdate();
				} 
			catch (SQLException e) 
				{
						BusinessException be = new BusinessException();
						be.ajouterErreur(15200);
						e.printStackTrace();
				}
			
		
	}

	@Override
	public void updateRetrait(Retrait retrait, int noArticle) throws BusinessException {
		String rue = retrait.getRue();
		String codePostal = retrait.getCodePostal();
		String ville = retrait.getVille();
			try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(UPDATE_RETRAIT))
				{
					stmt.setString(1, rue);
					stmt.setString(2, codePostal);
					stmt.setString(3, ville);
					stmt.setInt(4, noArticle);
						
						stmt.executeUpdate();
				} 
			catch (SQLException e) 
				{
					BusinessException be = new BusinessException();
					be.ajouterErreur(15201);
					e.printStackTrace();
				}
		
	}

	@Override
	public void deleteRetrait(int noArticle) throws BusinessException {
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(DELETE_RETRAIT))
			{
				stmt.setInt(noArticle, noArticle);
					stmt.executeUpdate();
			} catch (SQLException e) {
				BusinessException be = new BusinessException();
				be.ajouterErreur(15203);
				e.printStackTrace();
			}
		
	}

	@Override
	public Retrait getRetraitByID(int idArticle) throws BusinessException {
		Retrait retrait = null;
		
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(FIND_RETRAIT_BY_ID_ARTICLE))
			{
				stmt.setInt(1, idArticle);
				ResultSet rs = stmt.executeQuery();
					if(rs.next())
						{
							String rue = rs.getString("rue");
							String cp = rs.getString("code_postal");
							String ville = rs.getString("ville");
							retrait = new Retrait(rue, cp, ville);
						}
			} 
		catch (SQLException e) 
			{
				BusinessException be = new BusinessException();
				be.ajouterErreur(15203);
				e.printStackTrace();
			}
		return retrait;
	}
	
}
