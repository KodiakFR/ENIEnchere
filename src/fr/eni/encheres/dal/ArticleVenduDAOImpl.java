package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArticleVenduDAOImpl implements ArticleVenduDAO{

	private final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS where no_article=?;";
	
	@Override
	public void removeArticleVendu(int idArticle) throws SQLException {
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(DELETE_ARTICLE))
			{
			stmt.setInt(1, idArticle);	
			stmt.executeUpdate();
			}
			catch (SQLException e) {
				
			}
		
	}

}
