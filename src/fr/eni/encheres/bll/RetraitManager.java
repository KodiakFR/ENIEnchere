package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.RetraitDAO;

public class RetraitManager {

	private static RetraitManager instance;
	private RetraitDAO retraitDAO;
	
	private RetraitManager() {
		retraitDAO= DAOFactory.getRetraitDAO();
	}
	
	public static synchronized RetraitManager getInstance() {
		if(instance==null)
		{
			instance = new RetraitManager();
		}
		return instance;
	}
	
	
	//Ajout d'un lieu de retrait à l'ajout de l'article
	
	public void ajouterRetrait(Retrait retrait, int idArticle) throws BusinessException{
		String ville = retrait.getVille();
		String rue = retrait.getRue();
		String cp = retrait.getCodePostal();
		
		if(ville !=null && rue != null && cp != null)
			{
				retraitDAO.addRetrait(retrait, idArticle);
			}
	}
}
