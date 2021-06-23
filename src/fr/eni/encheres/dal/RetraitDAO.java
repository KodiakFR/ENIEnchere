package fr.eni.encheres.dal;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.Retrait;

public interface RetraitDAO {

	//Ajout du point de retrait lors de l'ajout d'un article
		public void addRetrait(Retrait retrait, int noArticle) throws BusinessException;
		
	//Update point de retrait
		public void updateRetrait(Retrait retrait, int noArticle) throws BusinessException;
		
	//Supprimer un retrait
		public void deleteRetrait(int noArticle) throws BusinessException;
		
	//Retrouver un retrait par l'id de l'article
		public Retrait getRetraitByID(int idArticle) throws BusinessException;
}
