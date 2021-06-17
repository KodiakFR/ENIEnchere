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
	
	/**
	 * Echec récuparation d'un utilisateur 
	 */
	public static final int SELECT_USER_ECHEC = 10102;
	
	
	
	
	
	//Erreurs ArticleVendu
	
	/**
	 * Echec récupération de l'article 
	 */
	
	public static final int RECUPERATION_ARTICLE_ECHEC=15003;
	
	/**
	 * Echec suppression article
	 */
	
	public static final int SUPPRESSION_ARTICLE_ECHEC=15000;
	
	/**
	 * Echec retour du propri�taire de l'articlevendu 
	 */
	
	public static final int CHECK_PROPRIO_ARTICLEVENDU_ECHEC=15001;
	
	/**
	 * Echec ajout de l'article 
	 */
	
	public static final int AJOUT_ARTICLE_ECHEC=15002;
	
	
	/**
	 * Echec RECUPERATION LISTE ARTICLES UTILISATEURS
	 */
	
	public static final int RECUP_LISTE_ARTICLE_ECHEC=15004;

	
	/**
	 * Echec RECUPERATION d'un article LISTE ARTICLES UTILISATEURS
	 */
	
	public static final int RECUP_ARTICLE_LISTE_ARTICLE_ECHEC=15005;

	
	/**
	 * Echec RECUPERATION d'un article LISTE ARTICLES UTILISATEURS
	 */
	
	public static final int RECUP_ID_CATEGORIE_ECHEC=15006;
	
	
	//Erreurs ArticleVendu

	
	/**
	 * Echec RECUPERATION de la liste des catégories existantes
	 */
	
	public static final int RECUP_LISTE_CATEGORIE_ECHEC=15007;
	
	
	
	// il faut mettre pour toutes les methodes de la dall
	
	
}
