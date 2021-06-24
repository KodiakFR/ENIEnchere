package fr.eni.encheres.bll;

public abstract class CodeResultatBLL {
	
	/**
	 * Echec l'adresse mail n'est pas au bon format ou trop longue
	 */
	public static final int REGLE_MAIL_NOM_ERREUR=20000;
	/**
	 * Echec le format du pseudo n'est pas bon ou il y a trop de caract�re
	 */
	public static final int REGLE_PSEUDO_ERREUR = 20001;
	
	/*
	 * Echec le pseudo est d�j� utilis�
	 */
	public static final int REGLE_PSEUDO_DEJA_UTIL_ERREUR = 20002;
	
	/*
	 * Echec l'adrsse mail est d�j� utilis�
	 */
	public static final int REGLE_MAIL_DEJA_UTIL_ERREUR = 20003;
	
	
	/*
	 * Echec le mot de passe n'est pas correct
	 */
	public static final int CHECK_VALIDATIONMDP_ECHEC = 20004;
	

	
	/*
	 * Echec récupération de la liste des catégories
	 */
	public static final int RECUPERATION_LISTE_CATEGORIE_ERREUR = 40000;
	
	/*
	 * Echec l'adrsse mail est d�j� utilis�
	 */
	public static final int RECUPERATION_UTILISATEUR_ERREUR = 40001;
	
	/*
	 * Echec ajout de l'article
	 */
	public static final int AJOUT_ARTICLE_ERREUR = 40002;
	
	/*
	 * Echec ajout ou MAJ lieu de retrait
	 */
	public static final int UPDATE_OR_ADD_RETRAIT_ERREUR = 40003;
	
	/*
	 * Echec ajout ou MAJ lieu de retrait
	 */
	public static final int ENCHERE_ERREUR = 40004;
	
	/*
	 * Echec modif ou suppression enchere
	 */
	public static final int MODIF_ENCHERE_ERREUR = 40005;
	
}
