package fr.eni.encheres.dal;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	
	// Insertion de la m�thode insert inscription // Dimitri
	public void insertInscription(Utilisateur utili) throws BusinessException;
	
	// Selection de la liste des pseudos pour v�rifier // Dimitri
	public Integer selectPseudo(String pseudo) throws BusinessException;
	
	// Methode de verif unicite email / Dimitri
	public Integer selectEmail(String email) throws BusinessException;
	
	// Méthode de verif si mdp correct pour la modification de profil / Dimitri
	public Integer selectPassword(String password) throws BusinessException;
	
	public Utilisateur SelectUser(String pseudo) throws BusinessException;

	public void updatePassword(String password, String userEmail) throws BusinessException;
	
	// Méthode de modification du profil de l'utilisateur / Dimitri
	public void updateProfil(Utilisateur utilisateur) throws BusinessException;
	
	// Methode de suppression du profil de l'utilisateur / Dimitri
	public void deleteProfil(String pseudo) throws BusinessException;

	//Méthode qui modifie le porte monnaie de l'utilisateur suite à une vente retourne son porte monnaie pour affichage éventuel
	public int updateCreditUtilisateur(String pseudoUtilisateur, int nouveauCredit) throws BusinessException;
}
