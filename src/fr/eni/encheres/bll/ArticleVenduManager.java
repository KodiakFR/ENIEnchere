package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.DAOFactory;

public class ArticleVenduManager {

	private static ArticleVenduManager instance;
	private ArticleVenduDAO articleVenduDAO;
	
	private ArticleVenduManager() {
		articleVenduDAO= DAOFactory.getArticleVenduDAO();
	}
	
	public static synchronized ArticleVenduManager getInstance() {
		if(instance==null)
		{
			instance = new ArticleVenduManager();
		}
		return instance;
	}
	
	//Ajout d'un article en vente par l'utilisateur et renvoi une liste des tous les articles de l'utilisateur
	public List<ArticleVendu> ajoutArticle(ArticleVendu article, Integer idvendeur, String categorie) throws BusinessException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		
		Integer idCategorie =  articleVenduDAO.checkCategorie(categorie);
		articleVenduDAO.addArticleVendu(article, idvendeur, idCategorie);
		
		listeArticles = articleVenduDAO.recupListArticleUtilisateur(idvendeur);
		System.out.println(listeArticles);
		
		return listeArticles;
	}
	
	//Suppression de l'article avant la date de fin  de l'enchère n'est pas terminée
	public  void cancelArticleVendu(String nomArticle, Integer IdVendeur) throws BusinessException {
		ArticleVendu art = articleVenduDAO.recupArticle(nomArticle, IdVendeur);
		Integer idArticleVendu = art.getNoArticle();
		
		if(LocalDate.now().compareTo(art.getDateFinEncheres())<0) {
			articleVenduDAO.removeArticleVendu(idArticleVendu);
		}
	}
	
	//Récupération des catégories
	public Set<Categorie> getListCategories() throws BusinessException{
		Set<Categorie> lstCat = articleVenduDAO.getListCategorie();
		
		return lstCat;
		
	}
	
	//Récupération de tous les articles selon l'état de la vente Créée = 1/En Cours = 2/Enchères Terminées = 3/ Retrait Effectué = 4
	public List<ArticleVendu> getListeEtatVente(String etatVente) throws BusinessException{
		List<ArticleVendu> lstArticle = new ArrayList<ArticleVendu>();
		int idEtatVente=0;
		switch (etatVente) {
		case "Créée":
			idEtatVente = 1;
			break;
			
		case "En Cours":
			idEtatVente = 2;
			break;
			
		case "Enchères Terminées":
			idEtatVente = 3;
			break;
			
		case "Retrait Effectué":
			idEtatVente = 4;
			break;

		}

		lstArticle = articleVenduDAO.recupListeArticleParEtatVente(idEtatVente);
		
		return lstArticle;
		
	}
}
