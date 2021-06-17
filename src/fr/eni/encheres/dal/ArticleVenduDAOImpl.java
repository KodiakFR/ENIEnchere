package fr.eni.encheres.dal;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;

public class ArticleVenduDAOImpl implements ArticleVenduDAO{

	private final String DELETE_ARTICLE =				"DELETE FROM ARTICLES_VENDUS where no_article=?;";
	
	private final String FIND_ID_CATEGORIE=				"SELECT no_categorie FROM CATEGORIES WHERE libelle=?;";
	private final String FIND_ARTICLE_FROM_USER =		"SELECT nom_article FROM ARTICLES_VENDUS WHERE no_utilisateur=?;";
	private final String CREATE_ARTICLE_FROM_USER = 	"SELECT no_article, description, date_debut_encheres, date_fin_encheres, "
														+ "prix_initial, prix_vente,no_categorie,etat_vente FROM ARTICLES_VENDUS "
														+ "WHERE nom_article=? AND no_utilisateur=?;";
	private final String FIND_ALL_CATEGORIES=			"SELECT no_categorie,libelle FROM CATEGORIES;";

	
	private final String INSERT_ARTICLE = 				"INSERT INTO ARTICLES_VENDUS VALUES(?,?,?,?,?,?,?,?,?);";
	
	
	@Override
	public void removeArticleVendu(Integer idArticle) {
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
	public void addArticleVendu(ArticleVendu article, Integer idVendeur, Integer IdCategorie) throws BusinessException {
		
		
		String nomArticle = article.getNomArticle();
		String description = article.getDescription();
		Date dateDebutEncheresSql = Date.valueOf(article.getDateDebutEncheres());
		Date dateFinEncheresSql = Date.valueOf(article.getDateFinEncheres());
		int miseAPrix = article.getMiseAPrix();
		int etatVente = article.getEtatVente();
		
		
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
			stmt.setInt(9, etatVente);
			int nbRows = stmt.executeUpdate();
			if(nbRows != 1)
			{
				throw new BusinessException();
			}
			
		} 
		catch (SQLException e) 
		{
			BusinessException be = new BusinessException();
			be.ajouterErreur(15002);
			e.printStackTrace();
		}
	}
	
	@Override
	public List<ArticleVendu> recupListArticleUtilisateur(Integer idUtilisateur){
		List<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(FIND_ARTICLE_FROM_USER))
		{
			stmt.setInt(1, idUtilisateur);
			ResultSet rs = stmt.executeQuery();
				while(rs.next())
				{
					String nomArticle = rs.getString("nom_article");
					ArticleVendu art;
					try {
						art = recupArticle(nomArticle, idUtilisateur);
						listeArticle.add(art);
					} catch (BusinessException e) {
						e.ajouterErreur(15005);
						e.printStackTrace();
					}
					
				}
			
		} catch (SQLException e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(15004);
			e.printStackTrace();
		}
		
		return listeArticle;
	}

	@Override
	public ArticleVendu recupArticle(String nomArticle, Integer idVendeur) throws BusinessException {
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

	@Override
	public Set<Categorie> getListCategorie() throws BusinessException {
		Set<Categorie> listCategorie = new HashSet<Categorie>();
			try(Connection con = ConnectionProvider.getConnection();Statement stmt = con.createStatement())
				{
					ResultSet rs = stmt.executeQuery(FIND_ALL_CATEGORIES);
					while(rs.next())
						{
							Integer noCategorie = rs.getInt("no_categorie");
							String nomCategorie = rs.getString("libelle");
							
							Categorie cat = new Categorie(noCategorie, nomCategorie);
							listCategorie.add(cat);
						}
				} 
			catch (SQLException e) 
				{
					BusinessException be = new BusinessException();
					be.ajouterErreur(15007);
					e.printStackTrace();
				}
		
		return listCategorie;
	}

	@Override
	public Integer checkCategorie(String nomCategorie) throws BusinessException {
		Integer idCategorie=null;
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(FIND_ID_CATEGORIE))
		{
			stmt.setString(1, nomCategorie);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
			idCategorie = rs.getInt(1);
			}
		} catch (SQLException e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(15006);
			e.printStackTrace();
		}
		return idCategorie;
	}
	
	
	
	private ArticleVendu mappingArticleVendu(ResultSet rs) throws SQLException{
		ArticleVendu u = null;
		
		Integer noArticle = rs.getInt("no_article");
		String description = rs.getString("description");
		LocalDate dateDebutEncheres = rs.getDate("date_debut_encheres").toLocalDate();
		LocalDate dateFinEncheres = rs.getDate("date_fin_encheres").toLocalDate();
		int miseAPrix = rs.getInt("prix_initial");
		int prixVente = rs.getInt("prix_vente");
		Integer noCategorie = rs.getInt("no_categorie");
		int etatVente = rs.getInt("etat_vente");
		
		u = new ArticleVendu(noArticle, description,dateDebutEncheres, dateFinEncheres,
				miseAPrix, prixVente, etatVente,noCategorie);
		
		
		return u;
	}

	
}
