package fr.eni.encheres.dal;

import java.util.List;
import java.util.Set;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;

public interface ArticleVenduDAO {

	
	//Permet la récupération de l'id de la catégorie
	
	public Integer checkCategorie(String nomCategorie) throws BusinessException;

	
	//Permet de supprimer l'article de la plateforme
	//vendu ou supprimer par le vendeur ou l'admin
	
	public void removeArticleVendu(Integer idArticle) throws BusinessException;
	
	
	
	//Ajouter un article à la BDD lié à un utilisateur et une catégorie
	
	public void addArticleVendu(ArticleVendu article, Integer idVendeur, Integer idCategorie) throws BusinessException;
	
	
	//Récupere tous les articles d'un utilisateur par son id
	
	public List<ArticleVendu> recupListArticleUtilisateur(Integer idUtilisateur) throws BusinessException;
	
	
	
	//Récupère un article complet d'un utilisateur
	
	public ArticleVendu recupArticle(String nomArticle, Integer idVendeur) throws BusinessException;
	
	
	//Récupère les catégories existantes
	
	public Set<Categorie> getListCategorie() throws BusinessException;
	
}
