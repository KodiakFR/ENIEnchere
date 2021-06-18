package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	//insert
	
	private final String INSERT_ENCHERE_EN_COURS= 	"INSERT INTO ENCHERES VALUES (?,?,?,?);";
	
	
	
	@Override
	public void ajouterEnchereEnCours(ArticleVendu article) throws BusinessException {
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(INSERT_ENCHERE_EN_COURS))
			{
			//Mapping
				Integer noUtilisateur = article.getNoUtilisateur();
				Integer noArticle = article.getNoArticle();
				Date dateFinEnchere = Date.valueOf(article.getDateFinEncheres());
				int montantEnchere = article.getMiseAPrix();
				
					stmt.setInt(1, noUtilisateur);
					stmt.setInt(2, noArticle);
					stmt.setDate(3, dateFinEnchere);
					stmt.setInt(4, montantEnchere);
				
			stmt.executeQuery();
			} 
		catch (SQLException e) 
			{
				BusinessException be = new BusinessException();
				be.ajouterErreur(15100);
				e.printStackTrace();
			}
		
	}

	@Override
	public void updateEnchere(ArticleVendu article, Integer idEncherisseur) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Enchere> getListeEncheresEnCours() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere deleteEnchere(ArticleVendu article) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
