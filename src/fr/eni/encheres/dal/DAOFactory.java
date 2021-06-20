package fr.eni.encheres.dal;

public class DAOFactory {
	
	public static ArticleVenduDAOImpl getArticleVenduDAO() {
		return new ArticleVenduDAOImpl();
	}
	
	public static UtilisateurDAOJdbcImpl getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}

	public static EnchereDAOJdbcImpl getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}
	
}
