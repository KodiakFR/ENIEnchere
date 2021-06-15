import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	private UtilisateurDAO utilisateurDAO;
	
	
	// Méthode de BLL pour inserer une nouvelle inscription
	public void AjouterInscription(Utilisateur utilisateur) throws BusinessException {
		
	}
	
	
	// Méthode qui vérifie que le pseudo n'est pas vide ou trop long
	private void validerPseudo(String nomPseudo, BusinessException businessException, Utilisateur utili) {
		if(nomPseudo == null | nomPseudo.trim().length() > 30) {
			businessException.ajouterErreur(CodeResultatBLL.REGLE_PSEUDO_ERREUR);
		}
		// méthode de vérif si déjà créé à faire
		if()
		
		
	}
	
	
	// Méthode qui vérifie si le mail n'est pas vide ou trop long
	private void validerMai(String nomMail, BusinessException businessException) {
		if(nomMail == null | nomMail.trim().length() > 30) {
			businessException.ajouterErreur(CodeResultatBLL.REGLE_MAIL_NOM_ERREUR);
		}
	}
}
	
