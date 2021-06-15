package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	private UtilisateurDAO utilisateurDAO;
	private static UtilisateurManager instance = null;
	
	public static synchronized UtilisateurManager getInstance() {
		if(instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	
	// M�thode de BLL pour inserer une nouvelle inscription
	public void AjouterInscription(Utilisateur utilisateur) throws BusinessException {
		
	}
	
	
	// M�thode qui v�rifie que le pseudo n'est pas vide ou trop long
	private void validerPseudo(String nomPseudo, BusinessException businessException, Utilisateur utili) {
		if(nomPseudo == null | nomPseudo.trim().length() > 30) {
			businessException.ajouterErreur(CodeResultatBLL.REGLE_PSEUDO_ERREUR);
		}
		// m�thode de v�rif si d�j� cr�� � faire
		//if()		
		
	}
	
	
	// M�thode qui v�rifie si le mail n'est pas vide ou trop long
	private void validerMai(String nomMail, BusinessException businessException) {
		if(nomMail == null | nomMail.trim().length() > 30) {
			businessException.ajouterErreur(CodeResultatBLL.REGLE_MAIL_NOM_ERREUR);
		}
	}
	
	
}
