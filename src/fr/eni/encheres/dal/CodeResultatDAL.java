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
	
	/**
	 * Echec modification mot de passe
	 */
	public static final int UPDATE_PASSWORD_ECHEC = 10103;
	
	/**
	 * Echec modification mot de passe
	 */
	public static final int UPDATE_CREDIT_ECHEC = 10104;
	
	/**
	 * Echec de la modification profil
	 */
	public static final int CHECK_LISTE_UPDATE_ECHEC = 10105;
	
	/**
	 * Echec de la suppression profil
	 */
	public static final int CHECK_LISTE_DELETE_ECHEC = 10106;
	
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
	
	/**
	 * Echec RECUPERATION de la liste des catégories existantes
	 */
	
	public static final int RECUP_LISTE_ARTICLES_PAR_ETATVENTE_ECHEC=15008;
	
	
	/**
	 * Echec RECUPERATION de la liste des catégories existantes
	 */
	
	public static final int RECUP_ARTICLE_BY_ID_ECHEC=15009;
	
	/**
	 * Echec update etat vente
	 */
	
	public static final int UPDATE_ETAT_VENTE_ECHEC=15010;
	
	/**
	 * Echec update prix de vente
	 */
	
	public static final int UPDATE_PRIX_DE_VENTE_ECHEC=15011;
	
	/**
	 * Echec récupération article par son nom
	 */
	
	public static final int RECUP_ARTICLE_BY_NOM_ECHEC=15012;
	
	/**
	 * Echec récupération id de l'article
	 */
	
	public static final int RECUP_ID_ARTICLE_ECHEC=15013;

	/**
	 * Echec update de l'article
	 */
	
	public static final int UPDATE_ARTICLE_ECHEC=15014;
	
	//Erreurs Encheres

	/**
	 * Echec RECUPERATION de la liste des catégories existantes
	 */
	
	public static final int AJOUT_ENCHERE_ECHEC=15100;
	
	/**
	 * Echec RECUPERATION enchere par id
	 */
	
	public static final int SELECT_ENCHERE_ECHEC=15101;
	
	/**
	 * Echec RECUPERATION enchere par id
	 */
	
	public static final int SELECT_MONTANT_ENCHERE_ECHEC=15102;
	
	/**
	 * Echec update enchere 
	 */
	
	public static final int UPDATE_ENCHERE_ECHEC=15103;
	

	
	//Erreurs retraits


	/**
	 * Echec insertion du lieu de retrait dans la base de données
	 */
	
	public static final int AJOUT_RETRAIT_ECHEC=15200;


	/**
	 * Echec update du lieu de retrait dans la base de données
	 */
	
	public static final int UPDATE_RETRAIT_ECHEC=15201;


	/**
	 * Echec insertion de du moyen de retrait dans la base de données
	 */
	
	public static final int DELETE_RETRAIT_ECHEC=15202;
	
	/**
	 * Echec récupération du lieu de retrait en bdd
	 */
	
	public static final int RECUP_RETRAIT_ECHEC=15203;
	

	
	// il faut mettre pour toutes les methodes de la dall
	
	
}
