package fr.eni.encheres.bo;

import java.time.LocalDate;

public class Enchere {
	
	//Attributes
	private Integer noUtilisateurMeilleureOffre;
	private Integer noArticle;
	private LocalDate dateEnchere;
	private int montantEnchere;
	
	
	//Constructors
	public Enchere() {
	}
	
	public Enchere(LocalDate dateEnchere, int montantEnchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	public Enchere(Integer noUtilisateurMeilleureOffre, Integer noArticle, LocalDate dateEnchere, int montantEnchere) {
		super();
		this.noUtilisateurMeilleureOffre = noUtilisateurMeilleureOffre;
		this.noArticle = noArticle;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	//Setter Getter
	
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	
	
	//toString
	
	public Integer getNoUtilisateurMeilleureOffre() {
		return noUtilisateurMeilleureOffre;
	}

	public void setNoUtilisateurMeilleureOffre(Integer noUtilisateurMeilleureOffre) {
		this.noUtilisateurMeilleureOffre = noUtilisateurMeilleureOffre;
	}

	public Integer getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}

	@Override
	public String toString() {
		return "Enchere [noUtilisateurMeilleureOffre=" + noUtilisateurMeilleureOffre + ", noArticle=" + noArticle
				+ ", dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + "]";
	}



}
