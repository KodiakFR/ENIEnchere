package fr.eni.encheres.dal;

public class DAOFactory {
	
	public static ArticleVenduDAOImpl getArticleVenduDAO() {
		return new ArticleVenduDAOImpl();
	}
	
	public static UtilisateurDAOImpl getUtilisateurDAO() {
		return new UtilisateurDAOImpl();
	}
}
