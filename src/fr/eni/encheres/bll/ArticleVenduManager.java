package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.DAOFactory;

public class ArticleVenduManager {

	private static ArticleVenduManager instance;
	private ArticleVenduDAO articleVenduDAO;
	
	private ArticleVenduManager() {
		articleVenduDAO= DAOFactory.getArticleVenduDAO();
	}
	
	public static synchronized ArticleVenduManager getInstance() {
		if(instance==null)
		{
			instance = new ArticleVenduManager();
		}
		return instance;
	}
	
	//Ajout d'un article en vente par l'utilisateur et renvoi une liste des tous les articles de l'utilisateur
	public void ajoutArticle(ArticleVendu article,String pseudoVendeur, int idvendeur, String categorie) throws BusinessException {
		
		
		
		articleVenduDAO.addArticleVendu(article,pseudoVendeur, idvendeur, categorie);
		
		
	}
	


	//Suppression de l'article avant la date de fin  de l'enchère n'est pas terminée
	public  void cancelArticleVendu(String nomArticle, String pseudoUtilisateur) throws BusinessException {
		ArticleVendu art = articleVenduDAO.recupArticleBYNomEtPseudoVendeur(nomArticle, pseudoUtilisateur);
		Integer idArticleVendu = art.getNoArticle();
		
		if(LocalDate.now().compareTo(art.getDateFinEncheres())<0) {
			articleVenduDAO.removeArticleVendu(idArticleVendu);
		}
	}
	
	//Récupération des catégories
	public Set<Categorie> getListCategories() throws BusinessException{
		Set<Categorie> lstCat = articleVenduDAO.getListCategorie();
		
		return lstCat;
		
	}
	
	//Récupération de tous les articles selon l'état de la vente
	public List<ArticleVendu> getListeEtatVente(String etatVente) throws BusinessException{
		List<ArticleVendu> lstArticle = new ArrayList<ArticleVendu>();
		int idEtatVente= transcriptEtatVenteToID(etatVente);

		lstArticle = articleVenduDAO.recupListeArticleParEtatVente(idEtatVente);
		
		return lstArticle;
		
	}
	
	//Récupération d'un article par son id
	public ArticleVendu getArticleByID(int idArticle) throws BusinessException{
		ArticleVendu article = articleVenduDAO.getArticleByIDArticle(idArticle);
		
		return article;
	}
	
	//Récupération d'un article par son nom
	
	public ArticleVendu getArticleByNomArticle(String nomArticle) throws BusinessException{
		ArticleVendu article = null;
		
		article = articleVenduDAO.recupArticleByNomArticle(nomArticle);
		
		return article;
	}
	
	//Update etat vente
	
	public void updateEtatVenteArticle(int idArticle, String etatVente) throws BusinessException{
		int idEtatVente = transcriptEtatVenteToID(etatVente);
		
			articleVenduDAO.updateEtatVente(idArticle, idEtatVente);
	}
	
	//Permet de transformer l'etat de vente en ID pour l'utiliser en base Créée= 1/En Cours = 2/Enchère Terminée = 3/ Retrait Effectué = 4
	private int transcriptEtatVenteToID(String etatVente) {
		int idEtat = 0;
		switch (etatVente) 
			{
				case "Créée":
					idEtat = 1;
					break;
					
				case "En cours":
					idEtat = 2;
					break;
					
				case "Enchère Terminée":
					idEtat = 3;
					break;
					
				case "Retrait Effectué":
					idEtat = 4;
					break;
	
			}
		return idEtat;
	}

}
