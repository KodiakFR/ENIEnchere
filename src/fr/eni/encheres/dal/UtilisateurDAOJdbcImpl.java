package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	// Requete SQL insertion lors inscription utilisateur
	private static final String INSERT_INSCRIP = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email"
			+ "telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur VALUES(?,?,?,?,?,?,?,?,?,0,0)";
	// Requete recuperation de toute la liste des pseudos
	private static final String SELECT_PSEUDO = "SELECT pseudo FROM UTILISATEURS";
	
	// Requete recuperation de toute la liste des emails
	private static final String SELECT_EMAIL = "SELECT email FROM UTILISATEURS";
	
	// Requete recuperation d'un utilisateur
	private static final String SELECT_USER = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur" 
			+"FROM UTILISATEURS WHERE pseudo = ?";
	
	
	
	
	// M�thode insertion des utilisateurs lors de l'inscription
	public void insertInscription(Utilisateur utili) throws BusinessException {
		
		if(utili == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection(); PreparedStatement rqt = cnx.prepareStatement(INSERT_INSCRIP, Statement.RETURN_GENERATED_KEYS); ){
			setParameter(rqt, utili);
			int nbRows = rqt.executeUpdate();
			if (nbRows == 1) {
				try (ResultSet rs = rqt.getGeneratedKeys();) {
					if (rs.next()) {
						utili.setNoUtilisateur(rs.getInt(1));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
		
		
	}
	
	
	// M�thode de r�cup�ration d'information d'unicit� pseudo 
	public List<String> selectPseudo() throws BusinessException {
		List<String> pseudoUtil = new ArrayList<String>();
		String util = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_PSEUDO);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				util = rs.getString("pseudo");
				pseudoUtil.add(util);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.CHECK_LISTE_PSEUDO_ECHEC);
			throw businessException;
		}
		return pseudoUtil;	
	}
	
	// M�thode de r�cup�ration d'information d'unicit� mail 
	public List<String> selectEmail() throws BusinessException {
		List<String> emailUtil = new ArrayList<String>();
		String util = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_EMAIL);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				util = rs.getString("pseudo");
				emailUtil.add(util);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.CHECK_LISTE_EMAIL_ECHEC);
			throw businessException;
		}
		return emailUtil;	
	}
	
	
	// Mapping pour r�cup�rer la liste des informations utilisateurs
	private Utilisateur mappingUtilisateur(ResultSet rs) throws SQLException {
		Utilisateur utilisateur = null;
		
		Integer noUti = rs.getInt("no_utilisateur");
		String pseudo = rs.getString("pseudo");
		String nom = rs.getString("nom");
		String prenom = rs.getString("prenom");
		String email = rs.getString("email");
		String tel = rs.getString("telephone");
		String rue = rs.getString("rue");
		String cp = rs.getString("code_postal");
		String ville = rs.getString("ville");
		String mdp = rs.getString("mot_de_passe");
		int credit = rs.getInt("credit");
		Boolean admin = rs.getBoolean("administrateur");
		
		utilisateur = new Utilisateur(noUti, pseudo, nom, prenom, email, tel, rue, cp, ville, mdp, credit, admin);
		return utilisateur;
		
	}
	
	
	// SETPARAMETER utilisateur pour la m�thode insertion inscription
	private void setParameter(PreparedStatement stmt, Utilisateur utilisateur) throws SQLException {
		stmt.setString(1, utilisateur.getPseudo());
		stmt.setString(2, utilisateur.getNom());
		stmt.setString(3, utilisateur.getPrenom());
		stmt.setString(4, utilisateur.getEmail());
		stmt.setString(5, utilisateur.getTelephone());
		stmt.setString(6, utilisateur.getRue());
		stmt.setString(7, utilisateur.getCodePostale());
		stmt.setString(8, utilisateur.getVille());
		stmt.setString(9, utilisateur.getMotDePasse());
		stmt.setBoolean(10, utilisateur.getAdministrateur());
		
	}
	
	//Méthode recuperation d'un utilisateur
	public Utilisateur SelectUser(Utilisateur utilisateur) throws BusinessException {
		System.out.println("je suis dans ma DAL");
		Utilisateur U = null;
		System.out.println(utilisateur.toString());
		try (Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement stmt= cnx.prepareStatement(SELECT_USER);){
			stmt.setString(1, utilisateur.getPseudo());
			try (ResultSet rs = stmt.executeQuery();){
				while(rs.next()) {
					U = mappingUtilisateur(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.SELECT_USER_ECHEC);
			throw businessException;
		}
		
		System.out.println(U.toString());
		return U;
	}
	
}
