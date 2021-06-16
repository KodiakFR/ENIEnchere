package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	private UtilisateurDAO utilisateurDAO;

	
	
	// Méthode de BLL pour inserer une nouvelle inscription

	private static UtilisateurManager instance = null;
	
	public static synchronized UtilisateurManager getInstance() {
		if(instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	
	// Méthode de BLL pour inserer une nouvelle inscription	
	public void AjouterInscription(Utilisateur utilisateur) throws BusinessException {
		BusinessException businessException = new BusinessException();
		
		this.validerMail(utilisateur.getEmail(), businessException);
		this.validerPseudo(utilisateur.getPseudo(), businessException);
		
		if(!businessException.hasErreurs()) {
			this.utilisateurDAO.insertInscription(utilisateur);
		}
		else {
			throw businessException;
		}
		
	}
	
	
	// Méthode qui vérifie que le pseudo n'est pas vide ou trop long
	private void validerPseudo(String nomPseudo, BusinessException businessException) {
		if(nomPseudo == null | nomPseudo.trim().length() > 30) {
			businessException.ajouterErreur(CodeResultatBLL.REGLE_PSEUDO_ERREUR);
		}
		
			List<String> listepseudo;
			try {
				listepseudo = this.utilisateurDAO.validationPseudo();
				if(listepseudo.contains(nomPseudo)) {
					businessException.ajouterErreur(CodeResultatBLL.REGLE_PSEUDO_DEJA_UTIL_ERREUR);
				}
			} catch (BusinessException e) {
				e.printStackTrace();
			}
				
		}
	
	
	
	
	
	// Méthode qui vérifie si le mail n'est pas vide ou trop long
	private void validerMail(String nomMail, BusinessException businessException) {
		if(nomMail == null | nomMail.trim().length() > 30) {
			businessException.ajouterErreur(CodeResultatBLL.REGLE_MAIL_NOM_ERREUR);
		}
		List<String> listeEmail;
		try {
			listeEmail = this.utilisateurDAO.validationEmail();
			if(listeEmail.contains(nomMail)) {
				businessException.ajouterErreur(CodeResultatBLL.REGLE_MAIL_DEJA_UTIL_ERREUR);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
				
	}
	

	
}

	
