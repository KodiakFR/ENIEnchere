package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {

	//Ajout de l'article aux enchère en cours au moment où date début = date actuelle
	//par défaut et jusqu'à la prmière enchère
	//Le propriètaire reste le vendeur
	//Le montant de l'enchère prend la mise à prix
	public void ajouterEnchereEnCours(ArticleVendu article) throws BusinessException;
	
	//Mise à jour de l'enchère
	public void updateEnchere(ArticleVendu article, Integer idEncherisseur) throws BusinessException;
	
	//Récupération des encheres en cours
	public List<Enchere> getListeEncheresEnCours() throws BusinessException;
	
	//Suppression d'une enchère
	//Cause Annulation ou Cause Fin de l'enchère
	//Retourne toutes les données pour récupérer les infos en cas de vente (NewProprietaire + prix final)
	public Enchere deleteEnchere(ArticleVendu article) throws BusinessException;
}
