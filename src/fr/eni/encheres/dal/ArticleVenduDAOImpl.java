package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;

public class ArticleVenduDAOImpl implements ArticleVenduDAO{

	private final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS where no_article=?;";
	
	private final String FIND_ID_USER_PROPRIO = "SELECT no_utilisateur FROM ARTICLES_VENDUS WHERE no_article=?;";
	private final String CREATE_ARTICLE_FROM_USER = "SELECT nom_article, description, date_debut_encheres, date_fin_encheres, "
													+ "prix_initial, prix_vente, no_utilisateur,no_categorie FROM ARTICLES_VENDUS "
													+ "WHERE no_utilisateur=?;";
	
	private final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS VALUES(?,?,?,?,?,?,?,?);";
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
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(FIND_ID_USER_PROPRIO))
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

	@Override
	public ArticleVendu addArticleVendu(ArticleVendu article, int idVendeur, int IdCategorie) throws BusinessException {
		ArticleVendu articleComplet = new ArticleVendu();
		
		String nomArticle = article.getNomArticle();
		String description = article.getDescription();
		Date dateDebutEncheresSql = Date.valueOf(article.getDateDebutEncheres());
		LocalDate dateDebutEncheres = article.getDateDebutEncheres();
		LocalDate dateFinEncheres = article.getDateFinEncheres();
		Date dateFinEncheresSql = Date.valueOf(article.getDateFinEncheres());
		int miseAPrix = article.getMiseAPrix();
		
		articleComplet.setNomArticle(nomArticle);
		articleComplet.setDescription(description);
		articleComplet.setDateDebutEncheres(dateDebutEncheres);
		articleComplet.setDateFinEncheres(dateFinEncheres);
		articleComplet.setMiseAPrix(miseAPrix);
		articleComplet.setPrixVente(miseAPrix);
		
		
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(INSERT_ARTICLE, PreparedStatement.NO_GENERATED_KEYS))
		{
			stmt.setString(1, nomArticle);
			stmt.setString(2, description);
			stmt.setDate(3, dateDebutEncheresSql);
			stmt.setDate(4, dateFinEncheresSql);
			stmt.setInt(5, miseAPrix);
			stmt.setInt(6, miseAPrix);
			stmt.setInt(7, idVendeur);
			stmt.setInt(8, IdCategorie);
			int nbRows = stmt.executeUpdate();
			if(nbRows == 1)
			{
				ResultSet rs = stmt.getGeneratedKeys();
				if(rs.next())
				{
					int key = rs.getInt(1);
					articleComplet.setNoArticle(key);
				}
			}
			
		} 
		catch (SQLException e) 
		{
			BusinessException be = new BusinessException();
			be.ajouterErreur(15002);
			e.printStackTrace();
		}
		return articleComplet;
	}
	
	@Override
	public List<String> recupListArticleUtilisateur(int idUtilisateur){
		List<String> listeArticle = new ArrayList<String>();
		
		
		return listeArticle;
	}

	@Override
	public ArticleVendu recupArticle(String nomArticle, int idVendeur) throws BusinessException {
		ArticleVendu articleComplet = new ArticleVendu();
			try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(CREATE_ARTICLE_FROM_USER))
			{
				stmt.setInt(1, idVendeur);
				
			} catch (SQLException e) {
				BusinessException be = new BusinessException();
				be.ajouterErreur(15003);
				e.printStackTrace();
			}
		
		return articleComplet;
	}
	
}
