package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
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
	
	//Ajout d'un article en vente par l'utilisateur
	public void ajoutArticle(ArticleVendu article, int idvendeur, String categorie) {
		Boolean catExist = articleVenduDAO.checkCategorie(caterogie);
		
		
	}
	
	//Suppression de l'article avant la date du début de l'enchère et si pas d'enchères
	public  void deleteArticleVendu(ArticleVendu article) throws BusinessException {
		int idArticleVendu = article.getNoArticle();
		
		if(article.getDateDebutEncheres().compareTo(LocalDate.now())<=0 && article.getMiseAPrix()==article.getPrixVente()) {
			articleVenduDAO.removeArticleVendu(idArticleVendu);
		}
	}
	
	
}
