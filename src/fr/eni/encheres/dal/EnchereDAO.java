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
	public void ajouterEnchereEnCours(ArticleVendu article, int idEncherisseur,int montantEnchere) throws BusinessException;
	
	//Mise � jour de l'ench�re
	public void updateEnchere(int idArticle, int idEncherisseur, int nouvelleEnchere) throws BusinessException;
	
	//Récupérer une enchere par l'id de l'article
	public Enchere getEnchereByIDArticle(int idArticle) throws BusinessException;

	//Récupérer le montant de l'enchère par l'id de l'article
	public int getMontantEnchereByIDArticle(int idArticle) throws BusinessException;
	
	//Vérification d'un enchère existante sur un article par son id
	public int checkEnchereExist(int idArticle) throws BusinessException;
	
	//Récuperer toutes les enchères 
	public List<Enchere> recupAllEncheres() throws BusinessException;
}
