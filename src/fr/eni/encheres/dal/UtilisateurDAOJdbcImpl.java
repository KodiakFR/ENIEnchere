package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.encheres.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl {
	// Requete SQL insertion lors inscription utilisateur
	private static final String INSERT_INSCRIP = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email"
			+ "telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur VALUES(?,?,?,?,?,?,?,?,?,?,?)  ";
	
	
	
	// Méthode insertion des utilisateurs lors de l'inscription
	
	public void insertInscription(Utilisateur utili) throws SQLException {
		
		if(utili == null) {
			//En anticipation : if permettant dans le cas où utilisateur null de renvoyer dans une exception personnalisée
			// a completer
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
		} catch (SQLException e) {
			e.printStackTrace();
			// Si erreur renvoyer dans la page exception personnalisée. A completer
		}
		
		
	}
	
	
	
	
	
	// Mapping pour récupérer la liste des informations utilisateurs
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
	
	
	// SETPARAMETER utilisateur pour la méthode insertion inscription
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
		stmt.setInt(10, utilisateur.getCredit());
		stmt.setBoolean(11, utilisateur.getAdministrateur());
		
	}
	
}
