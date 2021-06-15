package fr.eni.encheres.dal;

import java.sql.SQLException;

public interface ArticleVenduDAO {

	//A Faire plus tard
	//public void addArticleVendu(ArticleVendu art) throws SQLException;
	
	//Permet de supprimer l'article de la plateforme
	//vendu ou supprimer par le vendeur ou l'admin
	public void removeArticleVendu(int idArticle) throws SQLException;
	
	
	
}
