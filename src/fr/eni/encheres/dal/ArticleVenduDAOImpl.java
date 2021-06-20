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
	private final String FIND_ARTICLE_PAR_ETAT_VENTE=	"SELECT no_article,nom_article,description,date_debut_encheres,date_fin_encheres," 
														+ "prix_initial,prix_vente,no_utilisateur,no_categorie FROM ARTICLES_VENDUS " 
														+"WHERE etat_vente=?;";
	private final String FIND_ARTICLE_BY_ID=			"SELECT nom_article,description,date_debut_encheres,date_fin_encheres,"
														+ "prix_initial,prix_vente,no_utilisateur,no_categorie,etat_vente WHERE "
														+ "no_article=?;";
	
	private final String INSERT_ARTICLE = 				"INSERT INTO ARTICLES_VENDUS VALUES(?,?,?,?,?,?,?,?,?);";

	private final String UPDATE_ETAT_VENTE=				"UPDATE ARTICLES_VENDUS SET etat_vente='?' WHERE no_article=?;";
	private final String UPDATE_PRIX_VENTE=				"UPDATE ARTICLES_VENDUS SET prix_vente='?' WHERE no_article=?;";
	
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
	public void addArticleVendu(ArticleVendu article, int idVendeur, int IdCategorie) throws BusinessException {
		
		
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
	public List<ArticleVendu> recupListArticleUtilisateur(int idUtilisateur){
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
							art = recupArticleBYNomEtIDVendeur(nomArticle, idUtilisateur);
							listeArticle.add(art);
						} catch (BusinessException e) {
							e.ajouterErreur(15005);
							e.printStackTrace();
						}
					
					}
			} 
		catch (SQLException e) 
			{
				BusinessException be = new BusinessException();
				be.ajouterErreur(15004);
				e.printStackTrace();
			}
			
		return listeArticle;
	}

	@Override
	public ArticleVendu recupArticleBYNomEtIDVendeur(String nomArticle, int idVendeur) throws BusinessException {
		ArticleVendu articleComplet = new ArticleVendu();
			try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(CREATE_ARTICLE_FROM_USER))
			{
				stmt.setString(1, nomArticle);
				stmt.setInt(2, idVendeur);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next())
				articleComplet = mappingArticleVendu(rs);
				articleComplet.setNomArticle(nomArticle);
				articleComplet.setNoUtilisateur(idVendeur);
				
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
							int noCategorie = rs.getInt("no_categorie");
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
		} 
		catch (SQLException e) 
			{
				BusinessException be = new BusinessException();
				be.ajouterErreur(15006);
				e.printStackTrace();
			}
		return idCategorie;
	}
	
	
	
	private ArticleVendu mappingArticleVendu(ResultSet rs) throws SQLException{
		ArticleVendu u = null;
		
		int noArticle = 			rs.getInt("no_article");
		String description = 			rs.getString("description");
		LocalDate dateDebutEncheres = 	rs.getDate("date_debut_encheres").toLocalDate();
		LocalDate dateFinEncheres = 	rs.getDate("date_fin_encheres").toLocalDate();
		int miseAPrix = 				rs.getInt("prix_initial");
		int prixVente = 				rs.getInt("prix_vente");
		int noCategorie = 			rs.getInt("no_categorie");
		int etatVente = 				rs.getInt("etat_vente");
		
		u = new ArticleVendu(noArticle, description,dateDebutEncheres, dateFinEncheres,
				miseAPrix, prixVente, etatVente,noCategorie);
		
		
		return u;
	}

	@Override
	public List<ArticleVendu> recupListeArticleParEtatVente(int etatVente) throws BusinessException {
		List<ArticleVendu> lstArticle = new ArrayList<ArticleVendu>();
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(FIND_ARTICLE_PAR_ETAT_VENTE))
		{
			stmt.setInt(1, etatVente);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) 
				{
					int noArticle = 			rs.getInt("no_article");
					String nomArticle =				rs.getString("nom_article");
					String description = 			rs.getString("description");
					LocalDate dateDebutEncheres = 	rs.getDate("date_debut_encheres").toLocalDate();
					LocalDate dateFinEncheres = 	rs.getDate("date_fin_encheres").toLocalDate();
					int miseAPrix = 				rs.getInt("prix_initial");
					int prixVente = 				rs.getInt("prix_vente");
					int noUtilisateur = 		rs.getInt("no_utilisateur");
					int noCategorie = 			rs.getInt("no_categorie");
					
					ArticleVendu art = new ArticleVendu(noArticle,nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente, etatVente, noCategorie, noUtilisateur);
					lstArticle.add(art);
				}
			
		} 
		catch (SQLException e) 
			{
				BusinessException be = new BusinessException();
				be.ajouterErreur(15008);
				e.printStackTrace();
			}
		
		return lstArticle;
	}

	@Override
	public ArticleVendu getArticleByIDArticle(int idArticle) throws BusinessException {
		ArticleVendu article =null;
		
			try(Connection con =ConnectionProvider.getConnection();PreparedStatement stmt = con.prepareStatement(FIND_ARTICLE_BY_ID))
				{
					stmt.setInt(1, idArticle);
					ResultSet rs = stmt.executeQuery();
					
						while(rs.next())
							{
								
								String nomArticle =				rs.getString("nom_article");
								String description = 			rs.getString("description");
								LocalDate dateDebutEncheres = 	rs.getDate("date_debut_encheres").toLocalDate();
								LocalDate dateFinEncheres = 	rs.getDate("date_fin_encheres").toLocalDate();
								int miseAPrix = 				rs.getInt("prix_initial");
								int prixVente = 				rs.getInt("prix_vente");
								int noUtilisateur = 			rs.getInt("no_utilisateur");
								int noCategorie = 				rs.getInt("no_categorie");
								int etatVente=					rs.getInt("etat_vente");
								
								article = new ArticleVendu(idArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente, etatVente, noCategorie, noUtilisateur);
							
							}
				} 
			catch (SQLException e) 
				{
					BusinessException be = new BusinessException();
					be.ajouterErreur(15009);
					e.printStackTrace();
				}
		return article;
	}

	@Override
	public void updateEtatVente(int idArticle, int idEtatVente) throws BusinessException {
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(UPDATE_ETAT_VENTE))
			{
				stmt.setInt(1, idEtatVente);
				stmt.setInt(2, idArticle);
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	@Override
	public void updatePrixVente(int idArticle, int prixVente) throws BusinessException {
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(UPDATE_PRIX_VENTE))
		{
			stmt.setInt(1, prixVente);
			stmt.setInt(2, idArticle);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
