package fr.eni.encheres.bll;

public class UtilisateurManager {
	
	//Attributes
	
	private UtilisateurDAO daoUtilisateur;
	private static UtilisateurManager instance = null;
	
	// Constructors
	
	public UtilisateurManager() {
		daoUtilisateur = DAOFactory.getUtilisateurDAO();
	}

}
