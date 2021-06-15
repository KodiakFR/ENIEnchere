package fr.eni.encheres.dal;



/**
 * Les codes disponibles sont entre 10000 et 19999
 */

public abstract class CodeResultatDAL {
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJET_NULL=10000;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int INSERT_OBJET_ECHEC=10001;
	
	
	
	// il faut mettre pour toutes les methodes de la dall
	
	
}
