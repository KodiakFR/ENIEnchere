package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {

	//Ajout de l'article aux ench�re en cours au moment o� date d�but = date actuelle
	//par d�faut et jusqu'� la prmi�re ench�re
	//Le propri�taire reste le vendeur
	//Le montant de l'ench�re prend la mise � prix
	public void ajouterEnchereEnCours(ArticleVendu article) throws BusinessException;
	
	//Mise � jour de l'ench�re
	public void updateEnchere(int idArticle, int idEncherisseur, int nouvelleEnchere) throws BusinessException;
	
	//R�cup�ration des encheres en cours
	public List<Enchere> getListeEncheresEnCours() throws BusinessException;
	
	//Suppression d'une ench�re
	//Cause Annulation ou Cause Fin de l'ench�re
	//Retourne toutes les donn�es pour r�cup�rer les infos en cas de vente (NewProprietaire + prix final)
	public Enchere deleteEnchere(int idArticle) throws BusinessException;
	
	//Récupérer une enchere par l'id de l'article
	public Enchere getEnchereByIDArticle(int idArticle) throws BusinessException;
}
