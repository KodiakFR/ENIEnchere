package fr.eni.encheres.bll;

import java.time.LocalDate;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereManager {
	private static EnchereManager instance;
	private EnchereDAO enchereDAO;
	
	private EnchereManager() {
		enchereDAO= DAOFactory.getEnchereDAO();
	}
	
	public static synchronized EnchereManager getInstance() {
		if(instance==null)
		{
			instance = new EnchereManager();
		}
		return instance;
	}
	
	//Debut d'une enchère
	public boolean startEnchere(ArticleVendu article,int idEncherisseur,int montantEnchere) throws BusinessException{
		boolean execute = true;
		if(article.getEtatVente() == 2)
			{
				enchereDAO.ajouterEnchereEnCours(article,idEncherisseur, montantEnchere);
				
			}
		else
			{
				execute = false;
			}
		return execute;
		}
	
	//UPDATE enchere
	public String doNouvelleEnchere(ArticleVendu article, Utilisateur encherisseur, int nouvelleEnchere) throws BusinessException{
		String resultatEnchere = null;
		int idArticle = article.getNoArticle();
		Enchere enchereEnCours = enchereDAO.getEnchereByIDArticle(idArticle);
		int montantEnchere = enchereEnCours.getMontantEnchere();
		if(LocalDate.now().compareTo(enchereEnCours.getDateEnchere())<0)
			{
				
				if(nouvelleEnchere > montantEnchere )
					{
					int idEncherisseur = encherisseur.getNoUtilisateur();
						enchereDAO.ajouterEnchereEnCours(article, idEncherisseur, montantEnchere);;
						resultatEnchere = "Bravo, vous êtes le meilleur enchérisseur";
					}
				else
					{
						resultatEnchere = "Le montant de l'enchère doit être supérieur à l'enchère en cours";
					}
	
			}
		else
			{
				resultatEnchere = "l'enchère est terminée";
			}
		
		return resultatEnchere;
	}
	
	//Récupérer une enchère âr l'article
	
	public Enchere recuperationEnchereByArticle(ArticleVendu article) throws BusinessException{
		Enchere enchere = null;
		int idArticle = article.getNoArticle();
		int etatVente = article.getEtatVente();
			if(etatVente <3)
			{
				enchere = enchereDAO.getEnchereByIDArticle(idArticle);
			}
			
		return enchere;
	}
	
	//Récupérer montant de l'enchère
	public int recupMontantEnchere(int idArticle) throws BusinessException{
		int montantEnchere = enchereDAO.getMontantEnchereByIDArticle(idArticle);
		return montantEnchere;
	}
	
	//Vérifie si une enchère existe pour l'article
	public boolean verificationEnchereExist(ArticleVendu article) throws BusinessException{
		boolean enchereExist = false;
		int idArticle = article.getNoArticle();
		
		int resultatCount = enchereDAO.checkEnchereExist(idArticle);
			if(resultatCount>0)
				enchereExist = true;
				
				return enchereExist;
	}
}
