package fr.eni.encheres.dal;

import java.util.List;
import java.util.Set;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;

public interface ArticleVenduDAO {

	
	//Permet de supprimer l'article de la plateforme
	//vendu ou supprimer par le vendeur ou l'admin
	
	public void removeArticleVendu(int idArticle) throws BusinessException;
	
	
	
	//Ajouter un article à la BDD lié à un utilisateur et une catégorie
	
	public int addArticleVendu(ArticleVendu article, int idVendeur, String categorie) throws BusinessException;
	
	
	//Récupere tous les articles d'un utilisateur par son id
	
	public List<ArticleVendu> recupListArticleUtilisateur(String pseudoUtilisateur) throws BusinessException;
	
	
	
	//Récupère un article complet d'un utilisateur
	
	public ArticleVendu recupArticleBYNomEtPseudoVendeur(String nomArticle, String pseudoUtilisateur) throws BusinessException;
	
	
	//Récupère les catégories existantes
	
	public Set<Categorie> getListCategorie() throws BusinessException;
	
	//Récupère une liste d'article par son état de vente
	
	public List<ArticleVendu> recupListeArticleSelonFiltreAccueil(String motcle ,Integer ouvertes , Integer encours, Integer terminees, int numCategorie, int categoriesMax, String pseudoAchat, String pseudoVente) throws BusinessException;
	
	//Récupère un article par son id (utilisation pour les enchères principalement)
	
	public ArticleVendu getArticleByIDArticle(int idArticle) throws BusinessException;
	
	
	//Mise à jour de l'état de vente de l'article
	
	public void updateEtatVente(int idArticle, int idEtatVente) throws BusinessException;
	
	//Mise à jour du prix de vente
	
	public void updatePrixVente(int idArticle, int prixVente) throws BusinessException;
	
	//Récupération de l'id de l'article par son nom et le pseudo du vendeur
	
	public int getIdArticleByNomEtPseudo(String nomArticle, String pseudoVendeur) throws BusinessException;
	
}
