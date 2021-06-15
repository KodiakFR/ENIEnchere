package fr.eni.encheres.dal;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	
	// Insertion de la méthode insert inscription // Dimitri
	public void insertInscription(Utilisateur utili) throws BusinessException;
	
}
