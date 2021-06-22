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
	public void startEnchere(ArticleVendu article) throws BusinessException{
		
		if(LocalDate.now().compareTo(article.getDateDebutEncheres()) >=0)
			{
				enchereDAO.ajouterEnchereEnCours(article);
				
				ArticleVenduManager articleManager = ArticleVenduManager.getInstance();
					articleManager.updateEtatVenteArticle(article.getNoArticle(), "En cours");	
			}
		}
	
	//UPDATE enchere
	public void doNouvelleEnchere(int idArticle, Utilisateur encherisseur, int nouvelleEnchere) throws BusinessException{
		Enchere enchereEnCours = enchereDAO.getEnchereByIDArticle(idArticle);
		int creditAcheteur = encherisseur.getCredit();
		int montantEnchere = enchereEnCours.getMontantEnchere();
		int noUtilisateur = encherisseur.getNoUtilisateur();
		if(creditAcheteur-nouvelleEnchere>=0 && nouvelleEnchere > montantEnchere && LocalDate.now().compareTo(enchereEnCours.getDateEnchere())<0)
			{
				enchereDAO.updateEnchere(idArticle, noUtilisateur, nouvelleEnchere);
			}
	}
	
	//Récupérer montant de l'enchère
	public int recupMontantEnchere(int idArticle) throws BusinessException{
		int montantEnchere = enchereDAO.getMontantEnchereByIDArticle(idArticle);
		return montantEnchere;
	}
}
