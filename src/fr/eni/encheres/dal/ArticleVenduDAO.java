package fr.eni.encheres.dal;

import java.sql.SQLException;

import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduDAO {

	public void addArticleVendu(ArticleVendu art) throws SQLException;
	
	public void removeArticleVendu(int idArticle) throws SQLException;
	
	
	
}
