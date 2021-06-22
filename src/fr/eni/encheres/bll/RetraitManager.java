package fr.eni.encheres.bll;

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
	
}
