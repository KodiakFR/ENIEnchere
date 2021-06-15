package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.bll.BusinessException;

public class ArticleVenduDAOImpl implements ArticleVenduDAO{

	private final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS where no_article=?;";
	private final String FIND_USER_PROPRIO = "SELECT no_utilisateur FROM ARTICLES_VENDUS WHERE no_article=?;";
	
	@Override
	public void removeArticleVendu(int idArticle) {
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(DELETE_ARTICLE))
			{
			stmt.setInt(1, idArticle);	
			stmt.executeUpdate();
			}
			catch (SQLException e) 
			{
				BusinessException be = new BusinessException();
				be.ajouterErreur(15000);
				e.printStackTrace();
			}
		
	}

	@Override
	public int getProprietaireArticleVendu(int idArticleVendu) throws BusinessException {
		int idProprietaireArticleVendu = 0;
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(FIND_USER_PROPRIO))
		{
			stmt.setInt(1, idArticleVendu);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				idProprietaireArticleVendu = rs.getInt(1);
			}else
			{
				idProprietaireArticleVendu = 0;
			}
		}
		catch (SQLException e) 
		{
			BusinessException be = new BusinessException();
			be.ajouterErreur(15001);
			e.printStackTrace();
		}
		
		return idProprietaireArticleVendu;
	}

}
