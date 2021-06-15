package fr.eni.encheres.bo;

public class Categorie {
	
	//Attributes
	
	private Integer noCategorie;
	private String libelle;
	
	//Construtors
	
	public Categorie() {
	}

	public Categorie(Integer noCategorie, String libelle) {
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}

	//Getter Setter
	
	public Integer getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(Integer noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	 // toString
	
	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + "]";
	}
	
	

	
	
	

}
