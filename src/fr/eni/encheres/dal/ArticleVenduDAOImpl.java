package fr.eni.encheres.dal;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;

public class ArticleVenduDAOImpl implements ArticleVenduDAO{

	// DELETE
	private final String DELETE_ARTICLE =				"DELETE FROM ARTICLES_VENDUS where no_article=?;";
	
	//SELECT
	private final String FIND_ARTICLE_FROM_USER =		"Select nom_article from ARTICLES_VENDUS Inner join UTILISATEURS ON Articles_vendus.no_utilisateur = UTILISATEURS.no_utilisateur AND UTILISATEURS.pseudo=?;";
	
	private final String CREATE_ARTICLE_FROM_USER = 	"SELECT no_article, description, date_debut_encheres, date_fin_encheres, "
														+ "prix_initial, prix_vente,ARTICLES_VENDUS.no_utilisateur,no_categorie,etat_vente,images FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS "
														+ "ON ARTICLES_VENDUS.nom_article=? AND UTILISATEURS.pseudo=? AND Articles_vendus.no_utilisateur = UTILISATEURS.no_utilisateur;";
	
	private final String FIND_ALL_CATEGORIES=			"SELECT no_categorie,libelle FROM CATEGORIES;";
	
	private final String FIND_ARTICLE_PAR_FILTRE_ACCUEIL= "SELECT av.no_article,nom_article,description,date_debut_encheres,date_fin_encheres,"+
															" prix_initial,prix_vente,u.no_utilisateur,no_categorie,u.pseudo"+
															" , e.no_utilisateur, u2.pseudo as 'pseudo encherisseur'"+
															" FROM ARTICLES_VENDUS av INNER JOIN UTILISATEURS u  ON av.no_utilisateur = u.no_utilisateur "+
															" FULL OUTER JOIN ENCHERES e ON av.no_article = e.no_article "+
															" FULL OUTER JOIN UTILISATEURS u2 ON u2.no_utilisateur =e.no_utilisateur "+
															" WHERE nom_article LIKE (?) AND (etat_vente=? OR etat_vente=? OR etat_vente=?) AND (no_categorie>=? AND no_categorie<=?)"+
															" AND (u.pseudo =?  OR u.pseudo !=? ) AND u2.pseudo = ?;";
	
	private final String FIND_ARTICLE_BY_ID=			"SELECT nom_article,description,date_debut_encheres,date_fin_encheres,"
														+ "prix_initial,prix_vente,no_utilisateur,no_categorie,etat_vente,images FROM ARTICLES_VENDUS WHERE "
														+ "no_article=?;";
	
	private final String FIND_ID_CATEGORIE=				"SELECT no_categorie FROM CATEGORIES WHERE libelle=?;";
	
	private final String FIND_ID_ARTICLE=				"SELECT no_article FROM ARTICLES_VENDUS av INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur"
														+ "  AND nom_article=? AND u.pseudo=?;";
	
	//insert
	private final String INSERT_ARTICLE = 				"INSERT INTO ARTICLES_VENDUS VALUES(?,?,?,?,?,?,?,?,?,?);";

	//UPDATe
	private final String UPDATE_ETAT_VENTE=				"UPDATE ARTICLES_VENDUS SET etat_vente=? WHERE no_article=?;";
	private final String UPDATE_PRIX_VENTE=				"UPDATE ARTICLES_VENDUS SET prix_vente=? WHERE no_article=?;";
	
	@Override
	public boolean removeArticleVendu(int idArticle) {
		boolean articleSupprime = false;
		
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(DELETE_ARTICLE))
				{
				stmt.setInt(1, idArticle);	
				stmt.executeUpdate();
				articleSupprime = true;
				}
			catch (SQLException e) 
				{
					BusinessException be = new BusinessException();
					be.ajouterErreur(15000);
					e.printStackTrace();
				}
		return articleSupprime;
	}

	@Override
	public int addArticleVendu(ArticleVendu article,int idVendeur, String categorie) throws BusinessException {
		
		int idNouvelleVente = 0;
		
		int idCategorie = getIdCategorie(categorie);
		String nomArticle = article.getNomArticle();
		String description = article.getDescription();
		Date dateDebutEncheresSql = Date.valueOf(article.getDateDebutEncheres());
		Date dateFinEncheresSql = Date.valueOf(article.getDateFinEncheres());
		int miseAPrix = article.getMiseAPrix();
		int etatVente = article.getEtatVente();
		byte[] imageArticle = article.getImageArticle();
		
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(INSERT_ARTICLE,PreparedStatement.RETURN_GENERATED_KEYS))
			{
				stmt.setString(1, nomArticle);
				stmt.setString(2, description);
				stmt.setDate(3, dateDebutEncheresSql);
				stmt.setDate(4, dateFinEncheresSql);
				stmt.setInt(5, miseAPrix);
				stmt.setInt(6, 0);
				stmt.setInt(7, idVendeur);
				stmt.setInt(8, idCategorie);
				stmt.setInt(9, etatVente);
				stmt.setBytes(10, imageArticle);
				int nbRows = stmt.executeUpdate();
				if(nbRows == 1)
					{
						ResultSet rs = stmt.getGeneratedKeys();
							if(rs.next())
								{
									idNouvelleVente = rs.getInt(1);
								}
					}
			
			} 
		catch (SQLException e) 
			{
				BusinessException be = new BusinessException();
				be.ajouterErreur(15002);
				e.printStackTrace();
			}
		return idNouvelleVente;
	}
	


	@Override
	public List<ArticleVendu> recupListArticleUtilisateur(String pseudoUtilisateur){
		List<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(FIND_ARTICLE_FROM_USER))
			{
				stmt.setString(1, pseudoUtilisateur);
				ResultSet rs = stmt.executeQuery();
					while(rs.next())
					{
						String nomArticle = rs.getString("nom_article");
						ArticleVendu art;
						try {
							art = recupArticleBYNomEtPseudoVendeur(nomArticle, pseudoUtilisateur);
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
	public ArticleVendu recupArticleBYNomEtPseudoVendeur(String nomArticle, String pseudoUtilisateur) throws BusinessException {
		ArticleVendu articleComplet = new ArticleVendu();
			try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(CREATE_ARTICLE_FROM_USER))
			{
				stmt.setString(1, nomArticle);
				stmt.setString(2, pseudoUtilisateur);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next())
					{
						articleComplet = mappingArticleVendu(rs);
						articleComplet.setImageArticle(rs.getBytes("images"));
						articleComplet.setNomArticle(nomArticle);
						articleComplet.setPseudoUtilisateur(pseudoUtilisateur);
					}
				
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




	public List<ArticleVendu> recupListeArticleSelonFiltreAccueil(String motcle ,Integer ouvertes , Integer encours, Integer terminees, int numCategorie, int categorieMax, String pseudoAchat, String pseudoVente, String pseudoEnchereur) throws BusinessException {
		List<ArticleVendu> lstArticle = new ArrayList<ArticleVendu>();
		try(Connection con = ConnectionProvider.getConnection(); 
				PreparedStatement stmt = con.prepareStatement(FIND_ARTICLE_PAR_FILTRE_ACCUEIL))
		{
			stmt.setString(1, motcle);
			if (ouvertes == null) {
				stmt.setNull(2, Types.INTEGER);
			}
			else {
				stmt.setInt(2, ouvertes);
			}
			if (encours == null) {
				stmt.setNull(3, Types.INTEGER);
			}
			else {
				stmt.setInt(3, encours);
			}
			if (terminees == null) {
				stmt.setNull(4, Types.INTEGER);
			}
			else {
				stmt.setInt(4, terminees);
			}
			stmt.setInt(5, numCategorie);
			stmt.setInt(6, categorieMax);
			stmt.setString(7, pseudoAchat);
			stmt.setString(8, pseudoVente);
			stmt.setString(9, pseudoEnchereur);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) 
				{
					String nomArticle =				rs.getString("nom_article");
					LocalDate dateFinEncheres = 	rs.getDate("date_fin_encheres").toLocalDate();
					int prixVente = 				rs.getInt("prix_vente");
					String pseudoUtilisateur=		rs.getString("pseudo");
					
					ArticleVendu art = new ArticleVendu(nomArticle, dateFinEncheres, prixVente, pseudoUtilisateur);
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
								byte[] image =					rs.getBytes("images");
								
								article = new ArticleVendu(idArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente, noUtilisateur, noCategorie, etatVente,image);
							
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
				BusinessException be = new BusinessException();
				be.ajouterErreur(15010);
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
			BusinessException be = new BusinessException();
			be.ajouterErreur(15011);
			e.printStackTrace();
		}
		
	}

	
	@Override
	public int getIdArticleByNomEtPseudo(String nomArticle, String pseudoVendeur) throws BusinessException {
		int idArticle = 0;
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(FIND_ID_ARTICLE))
			{
				stmt.setString(1, nomArticle);
				stmt.setString(2, pseudoVendeur);
				
				ResultSet rs = stmt.executeQuery();
					if(rs.next())
						{
							idArticle = rs.getInt(1);
						}
			} 
		catch (SQLException e) 
			{
				BusinessException be = new BusinessException();
				be.ajouterErreur(15013);
				e.printStackTrace();
			}
		return idArticle;
	}
	
	
	
	private ArticleVendu mappingArticleVendu(ResultSet rs) throws SQLException{
		ArticleVendu u = null;
		
		int noArticle = 				rs.getInt("no_article");
		String description = 			rs.getString("description");
		LocalDate dateDebutEncheres = 	rs.getDate("date_debut_encheres").toLocalDate();
		LocalDate dateFinEncheres = 	rs.getDate("date_fin_encheres").toLocalDate();
		int miseAPrix = 				rs.getInt("prix_initial");
		int prixVente = 				rs.getInt("prix_vente");
		int noUtilisateur=				rs.getInt("no_utilisateur");
		int noCategorie = 				rs.getInt("no_categorie");
		int etatVente = 				rs.getInt("etat_vente");
		
		u = new ArticleVendu(noArticle, description,dateDebutEncheres,dateFinEncheres,miseAPrix,prixVente,etatVente,noCategorie,noUtilisateur);
		
		return u;
	}
	
	private int getIdCategorie(String categorie) {
		int idCategorie = 0;
		try(Connection con=ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(FIND_ID_CATEGORIE))
			{
				stmt.setString(1, categorie);
				ResultSet rs = stmt.executeQuery();
					while(rs.next())
						{
							idCategorie = rs.getInt("no_categorie");
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




}
