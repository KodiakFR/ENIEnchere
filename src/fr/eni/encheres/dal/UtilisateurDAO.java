package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	
	// Insertion de la m�thode insert inscription // Dimitri
	public void insertInscription(Utilisateur utili) throws BusinessException;
	
	// Selection de la liste des pseudos pour v�rifier // Dimitri
	public Integer selectPseudo(String pseudo) throws BusinessException;
	
	// Methode de verif unicite email
	public Integer selectEmail(String email) throws BusinessException;
	
	
	public Utilisateur SelectUser(Utilisateur utilisateur) throws BusinessException;

	public void updatePassword(String password, String userEmail) throws BusinessException;
	
	
	
}
