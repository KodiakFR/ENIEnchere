package fr.eni.encheres.dal;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduDAO {

	//A Faire plus tard
	//public void addArticleVendu(ArticleVendu art) throws SQLException;
	
	//Permet de supprimer l'article de la plateforme
	//vendu ou supprimer par le vendeur ou l'admin
	public void removeArticleVendu(int idArticle) throws BusinessException;
	
	//Récupère l'id du propriètaire de l'article
	public int getProprietaireArticleVendu(int idArticleVendu) throws BusinessException;
	
	public ArticleVendu addArticleVendu(ArticleVendu article, int idVendeur, int idCategorie) throws BusinessException;
}
