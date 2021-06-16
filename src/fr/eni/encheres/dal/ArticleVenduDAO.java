package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduDAO {

	
	//Permet de vérifier les c

	
	//Permet de supprimer l'article de la plateforme
	//vendu ou supprimer par le vendeur ou l'admin
	
	public void removeArticleVendu(int idArticle) throws BusinessException;
	
	
	
	//R�cup�re l'id du propri�taire de l'article
	
	public int getProprietaireArticleVendu(int idArticleVendu) throws BusinessException;
	
	
	
	//Ajouter un article à la BDD lié à un utilisateur et une catégorie
	
	public void addArticleVendu(ArticleVendu article, int idVendeur, int idCategorie) throws BusinessException;
	
	
	//Récupere tous les articles d'un utilisateur par son id
	
	public List<ArticleVendu> recupListArticleUtilisateur(int idUtilisateur) throws BusinessException;
	
	
	
	//Récupère un article complet d'un utilisateur
	
	public ArticleVendu recupArticle(String nomArticle, int idVendeur) throws BusinessException;
}
