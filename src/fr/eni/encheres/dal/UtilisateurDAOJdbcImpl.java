package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	
	// Requete SQL insertion lors inscription utilisateur.
	private static final String INSERT_INSCRIP = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,50,0)";
	// Requete recuperation en COUNT de pseudo
	private static final String COUNT_PSEUDO = "SELECT COUNT (*) as cnt FROM UTILISATEURS WHERE pseudo=?";
	
	// Requete recuperation en COUNT de emails
	private static final String COUNT_EMAIL = "SELECT COUNT (*) as cnt FROM UTILISATEURS WHERE email=?";
	
	// Requete recueperation du password en COUNT
	private static final String COUNT_PASSWORD = "SELECT COUNT (*) as cnt FROM UTILISATEURS WHERE mot_de_passe=?";
	
	// Requete recuperation d'un utilisateur
	private static final String SELECT_USER = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur"+
	" FROM UTILISATEURS WHERE pseudo=?";
	
	// Requete modification mot de passe
	private static final String UPDATE_PASSWORD = "UPDATE UTILISATEURS SET mot_de_passe=? WHERE email=?";
	
	// Requete Update formulaire modification profil
	private static final String UPDATE_PROFIL = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE pseudo=? ";
	
	//Requete Update credit utilisateur
	private final String UPDATE_CREDIT =	"UPDATE UTILISATEURS SET credit=? WHERE pseudo=?;";
	
	// Requete suppression du compte 
	private static final String DELETE_PROFIl = "DELETE FROM UTILISATEURS WHERE pseudo=?";
	
	
	
	// Méthode insertion des utilisateurs lors de l'inscription
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
	
	

	
	// Méthode de récupération d'information d'unicité pseudo 
		public Integer selectPseudo(String pseudo) throws BusinessException {
			Integer pseudoUtil = 0; 
			
			try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt= cnx.prepareStatement(COUNT_PSEUDO);){
				stmt.setString(1, pseudo);
				ResultSet rs = stmt.executeQuery();
					rs.next();
					pseudoUtil = rs.getInt("cnt");
					
				
			} catch (Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodeResultatDAL.CHECK_LISTE_PSEUDO_ECHEC);
				throw businessException;
			}
			System.out.println(pseudoUtil);
			return pseudoUtil;	
		}
	
	

		
	
	// Méthode de récupération d'information d'unicité mail 
		public Integer selectEmail(String email) throws BusinessException {
			Integer emailUtil = 0;
			try (Connection cnx = ConnectionProvider.getConnection();
					PreparedStatement stmt= cnx.prepareStatement(COUNT_EMAIL);){
				stmt.setString(1, email);
				ResultSet rs = stmt.executeQuery();
					rs.next();
					emailUtil = rs.getInt("cnt");
					System.out.println(emailUtil + "traitement du selectEmail valide ?");
					
			} catch (Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodeResultatDAL.CHECK_LISTE_EMAIL_ECHEC);
				throw businessException;
			}
			
			return emailUtil;	
		}
	
	// Méthode récupérationdu MDP en COUNT
		public Integer selectPassword(String password) throws BusinessException {
			Integer passwordUtil = 0;
			try (Connection cnx = ConnectionProvider.getConnection();
					PreparedStatement stmt= cnx.prepareStatement(COUNT_PASSWORD);){
				stmt.setString(1, password);
				ResultSet rs = stmt.executeQuery();
					rs.next();
					passwordUtil = rs.getInt("cnt");
					
					
			} catch (Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodeResultatDAL.CHECK_LISTE_EMAIL_ECHEC);
				throw businessException;
			}
			
			return passwordUtil;	
		}


		
	// Méthode modification du profil de l'utilisateur 
	public void updateProfil(Utilisateur utilisateur) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt= cnx.prepareStatement(UPDATE_PROFIL);){
		setParameter(stmt, utilisateur);
		stmt.setString(10, utilisateur.getPseudo());
		stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			// A FAIRE
			//businessException.ajouterErreur(CodeResultatDAL.CHECK_LISTE_PSEUDO_ECHEC);
			throw businessException;
		
		}
	}
	
	
	// Methode suppresion du profil de l'utilisateur
	public void deleteProfil(String pseudo) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt= cnx.prepareStatement(DELETE_PROFIl);){
			stmt.setString(1, pseudo);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			// A FAIRE
			//businessException.ajouterErreur(CodeResultatDAL.CHECK_LISTE_PSEUDO_ECHEC);
			throw businessException;
		}
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
		//stmt.setBoolean(10, utilisateur.getAdministrateur());
		
	}
	
	//Méthode recuperation d'un utilisateur
	public Utilisateur SelectUser(String pseudo) throws BusinessException {
		Utilisateur U = null;
		try (Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement stmt= cnx.prepareStatement(SELECT_USER);){
			stmt.setString(1, pseudo);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			U = mappingUtilisateur(rs);
			System.out.println(U);
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.SELECT_USER_ECHEC);
			throw businessException;
		}
		
		System.out.println(U.toString());
		return U;
	}


	//méthode pour modifier le mot de passe
	public void updatePassword(String password, String userEmail) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt= cnx.prepareStatement(UPDATE_PASSWORD);){
			stmt.setString(1, password);
			stmt.setString(2, userEmail);
			stmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.UPDATE_PASSWORD_ECHEC);
			throw businessException;
		}
		
	}




	@Override
	public int updateCreditUtilisateur(String pseudoUtilisateur, int nouveauCredit) throws BusinessException {
		int creditFinal = 0;
		
		try(Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(UPDATE_CREDIT))
			{
				stmt.setInt(1, nouveauCredit);
				stmt.setString(2, pseudoUtilisateur);
				
				stmt.executeUpdate();
			} 
		catch (SQLException e) 
			{
				BusinessException be = new BusinessException();
				be.ajouterErreur(10104);
				e.printStackTrace();
			}
		
		return creditFinal;
	}

	
}
