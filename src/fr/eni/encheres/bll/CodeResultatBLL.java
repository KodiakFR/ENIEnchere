package fr.eni.encheres.bll;

public abstract class CodeResultatBLL {
	
	/**
	 * Echec l'adresse mail n'est pas au bon format ou trop longue
	 */
	public static final int REGLE_MAIL_NOM_ERREUR=20000;
	/**
	 * Echec le format du pseudo n'est pas bon ou il y a trop de caractère
	 */
	public static final int REGLE_PSEUDO_ERREUR = 20001;
	
	/*
	 * Echec le pseudo est déjà utilisé
	 */
	public static final int REGLE_PSEUDO_DEJA_UTIL_ERREUR = 20002;
	
	/*
	 * Echec l'adrsse mail est déjà utilisé
	 */
	public static final int REGLE_MAIL_DEJA_UTIL_ERREUR = 20003;
	
}
