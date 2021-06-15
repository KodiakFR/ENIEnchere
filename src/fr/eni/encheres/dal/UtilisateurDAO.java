package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	
	// Insertion de la m�thode insert inscription // Dimitri
	public void insertInscription(Utilisateur utili) throws BusinessException;
	
	// Selection de la liste des pseudos pour v�rifier // Dimitri
	public List<String> validationPseudo() throws BusinessException;
	
	// Methode de verif unicite email
	public List<String> validationEmail() throws BusinessException;
	
	
}
