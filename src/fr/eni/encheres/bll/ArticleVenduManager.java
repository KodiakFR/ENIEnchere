package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
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
	
	//Suppression de l'article avant la date du début de l'enchère et si pas d'enchères
	public  void cancelArticleVendu(String nomArticle, Integer IdVendeur) throws BusinessException {
		ArticleVendu art = articleVenduDAO.recupArticle(nomArticle, IdVendeur);
		Integer idArticleVendu = art.getNoArticle();
		
		if(art.getDateDebutEncheres().compareTo(LocalDate.now())<0 && art.getMiseAPrix()==art.getPrixVente()) {
			articleVenduDAO.removeArticleVendu(idArticleVendu);
		}
	}
	
	//Récupération des catégories
	public Set<Categorie> getListCategories() throws BusinessException{
		Set<Categorie> lstCat = articleVenduDAO.getListCategorie();
		
		return lstCat;
		
	}
}
