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
	
	private final String FIND_ARTICLE_FROM_USER = "SELECT nom_article WHERE no_utilisateur=?;";
	private final String FIND_ID_USER_PROPRIO = "SELECT no_utilisateur FROM ARTICLES_VENDUS WHERE no_article=?;";
	private final String CREATE_ARTICLE_FROM_USER = "SELECT no_article, description, date_debut_encheres, date_fin_encheres, "
													+ "prix_initial, prix_vente,no_categorie,etat_vente FROM ARTICLES_VENDUS "
													+ "WHERE nom_article=? AND no_utilisateur=?;";
	
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
	public List<ArticleVendu> recupListArticleUtilisateur(int idUtilisateur){
		List<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(FIND_ARTICLE_FROM_USER))
		{
			stmt.setInt(1, idUtilisateur);
			ResultSet rs = stmt.executeQuery();
				while(rs.next())
				{
					String nomArticle = rs.getString("nom_article");
					ArticleVendu art = recupArticle(nomArticle, idUtilisateur);
					listeArticle.add(art);
				}
			
		} catch (SQLException e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(code);
			e.printStackTrace();
		}
		
		return listeArticle;
	}

	@Override
	public ArticleVendu recupArticle(String nomArticle, int idVendeur) throws BusinessException {
		ArticleVendu articleComplet = new ArticleVendu();
			try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(CREATE_ARTICLE_FROM_USER))
			{
				stmt.setString(1, nomArticle);
				stmt.setInt(2, idVendeur);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next())
				articleComplet = mappingArticleVendu(rs);
				articleComplet.setNomArticle(nomArticle);
				
			} catch (SQLException e) {
				BusinessException be = new BusinessException();
				be.ajouterErreur(15003);
				e.printStackTrace();
			}
		
		return articleComplet;
	}
	
	
	
	private ArticleVendu mappingArticleVendu(ResultSet rs) throws SQLException{
		ArticleVendu u = null;
		
		int noArticle = rs.getInt("no_article");
		String description = rs.getString("description");
		LocalDate dateDebutEncheres = rs.getDate("date_debut_encheres").toLocalDate();
		LocalDate dateFinEncheres = rs.getDate("date_fin_encheres").toLocalDate();
		int miseAPrix = rs.getInt("prix_initial");
		int prixVente = rs.getInt("prix_vente");
		int etatVente = rs.getInt("etat_vente");
		int noCategorie = rs.getInt("no_categorie");
		
		u = new ArticleVendu(noArticle, description,dateDebutEncheres, dateFinEncheres,
				miseAPrix, prixVente, etatVente,noCategorie);
		
		
		return u;
	}
	
}
