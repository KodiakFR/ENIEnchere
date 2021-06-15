package fr.eni.encheres.bll;

import java.time.LocalDate;

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
	
	public  void deleteArticleVendu(ArticleVendu article) throws BusinessException {
		int idArticleVendu = article.getNoArticle();
		
		if(article.getDateDebutEncheres().compareTo(LocalDate.now())<=0 ) {
			articleVenduDAO.removeArticleVendu(idArticleVendu);
		}
	}
	
	public void sellArticleVendu(ArticleVendu article, String pseudoAcheteur) throws BusinessException{
		int idArticleVendu = article.getNoArticle();
		int prixFinalArticle = article.getPrixVente();
		
		
		
	}
	
}
