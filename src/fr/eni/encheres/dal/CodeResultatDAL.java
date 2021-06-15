package fr.eni.encheres.dal;



/**
 * Les codes disponibles sont entre 10000 et 19999
 */

public abstract class CodeResultatDAL {
	
	/**
	 * Echec g�n�ral quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJET_NULL=10000;
	
	/**
	 * Echec g�n�ral quand erreur non g�r�e � l'insertion 
	 */
	public static final int INSERT_OBJET_ECHEC=10001;
	
	/**
	 * Echec g�n�ral quand erreur generation LISTE DE PSEUDO
	 */
	
	public static final int CHECK_LISTE_PSEUDO_ECHEC=10100;
	
	/**
	 * Echec g�n�ral quand erreur generation liste emails 
	 */
	
	public static final int CHECK_LISTE_EMAIL_ECHEC=10101;
	
	//Erreurs ArticleVendu
	
	/**
	 * Echec suppression article
	 */
	
	public static final int SUPPRESSION_ARTICLE_ECHEC=15000;
	
	/**
	 * Echec retour du propri�taire de l'articlevendu 
	 */
	
	public static final int CHECK_PROPRIO_ARTICLEVENDU_ECHEC=15001;
	
	// il faut mettre pour toutes les methodes de la dall
	
	
}
