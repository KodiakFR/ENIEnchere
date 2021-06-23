package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	//insert
	
	private final String INSERT_ENCHERE_EN_COURS= 	"INSERT INTO ENCHERES VALUES (?,?,?,?);";
	
	//select
	
	private final String SELECT_BY_ID=				"SELECT no_utilisateur,date_enchere,montant_enchere FROM ENCHERES "
													+ "WHERE no_article=?;";
	private final String SELECT_MONTANT_BY_ID=		"SELECT montant_enchere FROM ENCHERES WHERE no_article=?;";
	
	//update
	
	private final String UPDATE_ENCHERE=			"UPDATE ENCHERES SET no_utilisateur=?, montant_enchere=? WHERE no_article=?;";
	
	@Override
	public void ajouterEnchereEnCours(ArticleVendu article,int idEncherisseur, int montantEnchere) throws BusinessException {
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(INSERT_ENCHERE_EN_COURS))
			{
				Integer noArticle = article.getNoArticle();
				Date dateFinEnchere = Date.valueOf(article.getDateFinEncheres());
				
					stmt.setInt(1, idEncherisseur);
					stmt.setInt(2, noArticle);
					stmt.setDate(3, dateFinEnchere);
					stmt.setInt(4, montantEnchere);
				
			stmt.executeQuery();
			} 
		catch (SQLException e) 
			{
				BusinessException be = new BusinessException();
				be.ajouterErreur(15100);
				e.printStackTrace();
			}
		
	}

	@Override
	public void updateEnchere(int idArticle, int idEncherisseur, int nouvelleEnchere) throws BusinessException {
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(UPDATE_ENCHERE))
			{
				stmt.setInt(1, idEncherisseur);
				stmt.setInt(2, nouvelleEnchere);
				stmt.setInt(3, idArticle);
				
				stmt.executeUpdate();
			} 
		catch (SQLException e) 
			{
				BusinessException be = new BusinessException();
				be.ajouterErreur(15103);
				e.printStackTrace();
			}
		
	}

	@Override
	public List<Enchere> getListeEncheresEnCours() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere deleteEnchere(int idArticle) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere getEnchereByIDArticle(int idArticle) throws BusinessException {
		Enchere enchere = null;
		
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(SELECT_BY_ID))
			{
				stmt.setInt(1, idArticle);
				ResultSet rs = stmt.executeQuery();
					if(rs.next())
						{
							int noUtilisateur = rs.getInt("no_utilisateur");
							LocalDate dateEnchere = rs.getDate("date_enchere").toLocalDate();
							int montantEnchere = rs.getInt("montant_enchere");
							
							enchere = new Enchere(noUtilisateur, idArticle, dateEnchere, montantEnchere);
						}
			} 
		catch (SQLException e) 
			{
				BusinessException be = new BusinessException();
				be.ajouterErreur(15101);
				e.printStackTrace();
			}
		
		return enchere;
	}

	@Override
	public int getMontantEnchereByIDArticle(int idArticle) throws BusinessException {
		int montantEnchere = 0;
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(SELECT_MONTANT_BY_ID))
			{
				ResultSet rs = stmt.executeQuery();
				if(rs.next())
					{
						montantEnchere = rs.getInt("montant_enchere"); 
					}
			} 
		catch (SQLException e) 
			{
				BusinessException be = new BusinessException();
				be.ajouterErreur(15102);
				e.printStackTrace();
			}
		return montantEnchere;
	}

}
